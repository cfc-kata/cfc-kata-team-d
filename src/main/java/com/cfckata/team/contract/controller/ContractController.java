package com.cfckata.team.contract.controller;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.contract.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    /**
     *
     * @param contractId
     * @return
     */
    @GetMapping("/{contractId}")
    public ContractResponse findContract(@PathVariable String contractId) {
        Contract  contract =contractService.findById(contractId);
        if(contract==null){
           return new ContractResponse("合同不存在");
        }
        ContractResponse response= contract.toResponse();
        return response;
    }


    /**
     *
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContractResponse createContract(@RequestBody CreateContractRequest request) {
        Contract contract=  contractService.createContract(request);
        return contract.toResponse();
    }

}
