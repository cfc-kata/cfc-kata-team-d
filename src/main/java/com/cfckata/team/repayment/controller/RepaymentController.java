package com.cfckata.team.repayment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cfckata.team.repayment.domain.Repayment;
import com.cfckata.team.repayment.response.RepaymentBatchResponse;
import com.cfckata.team.repayment.response.RepaymentResponse;
import com.cfckata.team.repayment.service.RepaymentService;

@RestController
@RequestMapping("/repayment")
public class RepaymentController {

    private RepaymentService repaymentService;

    public RepaymentController(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }

    @GetMapping("/{id}")
    public RepaymentResponse findRepayment(@PathVariable String id) {
        Repayment repayment = repaymentService.findById(id);
        return new RepaymentResponse(repayment);
    }

    @PutMapping("/{repaymentId}")
    @ResponseStatus(HttpStatus.OK)
    public RepaymentResponse createRepayment(@PathVariable String repaymentId) {
  
        return null;
    }
    
    @PutMapping("/{repaymentDate}")
    @ResponseStatus(HttpStatus.OK)
    public RepaymentBatchResponse batchRepayment(@PathVariable String repaymentDate) {
        return null;
    }
}
