package com.cfckata.team.contract.service;

import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.dao.domain.ContractFactory;
import com.cfckata.team.contract.request.CreateContractRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

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
     * @param id
     * @return
     */
    public Contract findById(String id){
        return contractRepository.selectById(id);
    }

    /**
     * 生成合同
     * @param request
     * @return
     */
    public Contract createContract(CreateContractRequest request){
        Contract contract =  contractFactory.getContract(request);
        contract.allowAmt(request.getApplyAmt(),contractRepository.selectLimitByAge(request.getCustomer().getAge()));
        contract.allowTerm(request.getApplyTerm());
        contractRepository.insert(contract);
        return contract;
    }
}
