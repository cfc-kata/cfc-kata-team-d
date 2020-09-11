package com.cfckata.team.contract.dao.domain;

import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.customer.CustomerRepository;
import com.cfckata.team.sales.domain.SalesOrder;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;
import org.springframework.stereotype.Service;

@Service
public class ContractFactory {

    private ContractRepository contractRepository;

    private CustomerRepository customerRepository;

    public ContractFactory(ContractRepository contractRepository,CustomerRepository customerRepository) {
        this.contractRepository= contractRepository;
        this.customerRepository=customerRepository;

    }


    /**
     * 生产一个合同
     * @param request
     * @return
     */
    public Contract getContract(CreateContractRequest request) {
        if (customerRepository.findById(request.getCustomer().getId()) == null) {
            throw new IllegalArgumentException("Customer not exists.");
        }
        Contract contract=new Contract();
        contract.setCustomerId(request.getCustomer().getId());
        return contract;
    }

}
