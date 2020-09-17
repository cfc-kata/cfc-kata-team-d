package com.cfckata.team.loan.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.loan.LoanRepository;
import com.cfckata.team.loan.RepaymentPlanRepository;
import com.cfckata.team.loan.dao.LoanFactory;
import com.cfckata.team.loan.dao.RepaymentPlanFactory;
import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.domain.RepaymentPlan;
import com.cfckata.team.loan.domain.RepaymentType;
import com.cfckata.team.loan.proxy.LoanProxy;
import com.cfckata.team.loan.request.LoanSendRequest;

@Service
public class LoanService {

	private LoanRepository loanRepository;
	private RepaymentPlanRepository repaymentPlanRepository;
	private ContractService contractService;
	private LoanFactory loanFactory;
	private RepaymentPlanFactory repaymentPlanFactory;
    private LoanProxy loanProxy;
	
	public LoanService(LoanRepository loanRepository, RepaymentPlanRepository repaymentPlanRepository,
			ContractService contractService, LoanFactory loanFactory, RepaymentPlanFactory repaymentPlanFactory,
			LoanProxy loanProxy) {
		this.loanRepository = loanRepository;
		this.repaymentPlanRepository = repaymentPlanRepository;
		this.contractService = contractService;
		this.loanFactory = loanFactory;
		this.repaymentPlanFactory = repaymentPlanFactory;
		this.loanProxy = loanProxy;
	}

	public Loan findById(String id) {
    	return loanRepository.findById(id);
	}
	
	public String createLoanAndPlans(LoanSendRequest request) {
		beforeValidate(request);
		String retCode = loanProxy.pay(request.getRepaymentBankAccount(), request.getApplyAmount());
		if("success".equals(retCode)){
			return saveLoanAndPlans(request);
		}
		return null;
	}

	private void beforeValidate(LoanSendRequest request) {
		Contract contract = contractService.findById(request.getContractId());
		if(contract == null){
			throw new IllegalArgumentException("Contract not exists.");
		}
		if(request.getApplyAmount().compareTo(contract.getAmt()) > 0){
			throw new IllegalArgumentException("放款总额不能超过合同总额度");
		}
		if(contract.getOverDate().compareTo(new Date()) > 0){
			throw new IllegalArgumentException("合同过期不能放款");
		}
		if(!RepaymentType.DEBJ.equals(request.getRepaymentType())){
			throw new IllegalArgumentException("目前只支持等额本金还款方式");
		}
	}

	private String saveLoanAndPlans(LoanSendRequest request) {
		Loan record = loanFactory.createLoan(request);
		loanRepository.insert(record);
		List<RepaymentPlan> plans = repaymentPlanFactory.createPlans(record);
		repaymentPlanRepository.batchInsert(plans);
		return record.getLoanId();
	}


}
