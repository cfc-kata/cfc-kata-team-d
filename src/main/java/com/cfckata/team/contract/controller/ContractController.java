package com.cfckata.team.contract.controller;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.core.DataResponse;
import com.cfckata.team.exception.ServiceException;
import com.cfckata.team.utils.JsosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private Logger log = LoggerFactory.getLogger(ContractController.class);

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    /**
     * 根据合同号查询合同信息
     * @param contractId
     * @return
     */
    @PostMapping(path="/{contractId}")
    public  DataResponse<ContractResponse> getContract(@PathVariable String contractId) {
        if(StringUtils.isEmpty(contractId)){
            return DataResponse.fail("1100010","合同号不能为空");
        }
        Contract  contract =contractService.findById(contractId);
        if(contract==null){
           return DataResponse.fail("1100001","合同不存在");
        }
        DataResponse<ContractResponse> response= DataResponse.succeed(contract.toResponse());
        return  response;
    }


    /**
     * 创建合同
     * @param request
     * @return
     */
    @PostMapping(path="/create",produces = {"application/json;charset=utf-8"},consumes={"application/json;charset=utf-8"})
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse<ContractResponse> createContract(@RequestBody CreateContractRequest request) {
        if(request==null || request.getCustomer()==null || request.getApplyAmt()==null || request.getApplyTerm()<=0||request.getApplyRate()==null){
            return DataResponse.fail("1000010","参数不全");
        }
        Contract contract=null;
        try {
             contract = contractService.createContract(request);
        }catch(ServiceException e){
            return DataResponse.fail(e.getRetCode(),e.getRetMsg());
        }catch(Exception e){
            return DataResponse.fail("1000001","处理失败");
        }
        DataResponse<ContractResponse>  response =   DataResponse.succeed(contract.toResponse());
        return  response;
    }

}
