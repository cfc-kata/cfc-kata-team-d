package com.cfckata.team.loan.controller;

import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.request.LoanSendRequest;
import com.cfckata.team.loan.response.LoanQryResponse;
import com.cfckata.team.loan.response.LoanSendResponse;
import com.cfckata.team.loan.service.LoanService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    
    @GetMapping("/{loanId}")
    public LoanQryResponse loanQry(@PathVariable String loanId) {
    	Loan loan = loanService.findById(loanId);
        return new LoanQryResponse(loan);
    }

    @PostMapping("/sendLoan")
    @ResponseStatus(HttpStatus.OK)
    public LoanSendResponse sendLoan(@RequestBody LoanSendRequest request) {
    	String loanId = loanService.sendLoan(request);
        return new LoanSendResponse(loanId);
    }

}
