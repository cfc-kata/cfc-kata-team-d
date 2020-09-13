package com.cfckata.team.contract.dao.domain;

import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.customer.Customer;
import com.cfckata.team.customer.CustomerRepository;
import com.cfckata.team.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractFactory {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ContractFactory(ContractRepository contractRepository,CustomerRepository customerRepository) {
        this.contractRepository= contractRepository;
        this.customerRepository=customerRepository;

    }


    /**
     * 生成合同
     * @param request
     * @return
     */
    public Contract getContract(CreateContractRequest request) {
        Customer customer =customerRepository.findById(request.getCustomer().getId());
        if ( customer== null) {
            throw new IllegalArgumentException("Customer not exists.");
        }
        Contract contract=new Contract();
        contract.setId(DateUtils.getCurDT()+DateUtils.getCurTM());
        contract.setCustomerId(request.getCustomer().getId());
        contract.setCustomer(customer);
        return contract;
    }

}
