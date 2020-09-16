package com.cfckata.team.loan;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.cfckata.team.loan.dao.mapper.LoanMapper;
import com.cfckata.team.loan.domain.Loan;

@Repository
public class LoanRepository {
    private LoanMapper loanMapper;

    public LoanRepository(LoanMapper loanMapper) {
		this.loanMapper = loanMapper;
	}

	public Loan findById(String id) {
		Loan loan = loanMapper.selectByPrimaryKey(id);
        if (loan == null) {
            throw new EntityNotFoundException("loan(" + id + ") not found");
        }

        return loan;
    }
	
	public int insert(Loan loan) {
		return loanMapper.insert(loan);
	}

	public int updateByPrimaryKey(Loan record) {
    	return loanMapper.updateByPrimaryKey(record);
    }

	public int delete(Loan orderDO) {
    	return loanMapper.delete(orderDO);
    }
   
}
