package com.cfckata.team.repayment.repository;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.cfckata.team.repayment.dao.RepaymentDO;
import com.cfckata.team.repayment.domain.Repayment;
import com.cfckata.team.repayment.mapper.RepaymentDOMapper;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;

@Repository
public class RepaymentRepository {
    private RepaymentDOMapper repaymentDOMapper;

    public RepaymentRepository(RepaymentDOMapper repaymentDOMapper) {
        this.repaymentDOMapper = repaymentDOMapper;
    }

    public Aggregate<Repayment> findById(String id) {

        RepaymentDO repaymentDO = repaymentDOMapper.selectByPrimaryKey(id);
        if (repaymentDO == null) {
            throw new EntityNotFoundException("Repayment(" + id + ") not found");
        }

        Repayment repayment = repaymentDO.toRepayment();
        return AggregateFactory.createAggregate(repayment);
    }
}
