package com.cfckata.team.repayment.service;

import org.springframework.stereotype.Service;

import com.cfckata.team.repayment.domain.Repayment;
import com.cfckata.team.repayment.repository.RepaymentRepository;
import com.github.meixuesong.aggregatepersistence.Aggregate;

@Service
public class RepaymentService {
	
	private RepaymentRepository repaymentRepository;

	public RepaymentService(RepaymentRepository repaymentRepository) {
		this.repaymentRepository = repaymentRepository;
	}

	public Repayment findById(String id) {
        Aggregate<Repayment> repaymentAggregate = repaymentRepository.findById(id);
        return repaymentAggregate.getRoot();
    }
}
