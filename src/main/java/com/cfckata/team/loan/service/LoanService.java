package com.cfckata.team.loan.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.loan.LoanRepository;
import com.cfckata.team.loan.RepaymentPlanRepository;
import com.cfckata.team.loan.dao.LoanFactory;
import com.cfckata.team.loan.dao.RepaymentPlanFactory;
import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.domain.RepaymentPlan;
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
		Contract contract = contractService.findById(request.getContractId());
		if(contract == null){
			throw new IllegalArgumentException("Contract not exists.");
		}
		//放款总额不能超过合同总额度；
		if(request.getApplyAmount().compareTo(contract.getAmt()) > 0){
			throw new IllegalArgumentException("放款总额不能超过合同总额度");
		}
		//合同过期不能放款；
		if(contract.getOverDate().compareTo(new Date()) > 0){
			throw new IllegalArgumentException("合同过期不能放款");
		}
		String retCode = loanProxy.pay(request.getRepaymentBankAccount(), request.getApplyAmount());
		if("success".equals(retCode)){
			//银行支付接口失败时，本次操作失败，需要重新发起放款
			return saveLoanAndPlans(request);
		}else{
			return null;
		}
	}

	@Transactional
	private String saveLoanAndPlans(LoanSendRequest request) {
		Loan record = loanFactory.createLoan(request);
		loanRepository.insert(record);
		List<RepaymentPlan> plans = repaymentPlanFactory.createPlans(record);
		for(RepaymentPlan plan : plans){
			repaymentPlanRepository.insert(plan);
		}
		if(record == null){
			return null;
		}
		return record.getLoanId();
	}


}
