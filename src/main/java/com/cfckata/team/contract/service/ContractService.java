package com.cfckata.team.contract.service;

import com.cfckata.team.contract.constants.ContractConstant;
import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.dao.domain.ContractFactory;
import com.cfckata.team.contract.request.CreateContractRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    @Autowired
    private ContractFactory contractFactory;

    @Autowired
    private ContractRepository contractRepository;

    public ContractService(){}

    public ContractService(ContractFactory contractFactory,ContractRepository contractRepository) {
        this.contractFactory = contractFactory;
        this.contractRepository=contractRepository;
    }


    /**
     * 查询合同
     * @param id 合同ID
     * @return
     */
    public Contract findById(String id){
        return contractRepository.selectById(id);
    }

    /**
     * 创建并生成合同
     * @param request
     * @return
     */
    public Contract createContract(CreateContractRequest request){
        Contract contract =  contractFactory.getContract(request);
        contract.checkAndSetAmt(request.getApplyAmt(),contractRepository.selectLimitByAge(request.getCustomer().getAge()));
        contract.checkAndSetTerm(request.getApplyTerm());
        contract.checkAndSetRate(request.getApplyRate());
        contract.setStatus(ContractConstant.CONTRACT_STATUS_ACTIVE);
        contractRepository.insert(contract);
        return contract;
    }
}
