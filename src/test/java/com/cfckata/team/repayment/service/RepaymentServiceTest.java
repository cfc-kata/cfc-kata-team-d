/**
 * @title RepaymentServiceTest.java
 * @author Thinkpad
 * @date 2020年9月11日 下午2:36:01
 */
package com.cfckata.team.repayment.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cfckata.team.repayment.domain.Repayment;
import com.cfckata.team.repayment.repository.RepaymentRepository;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;

/**
 * @Description <描述>
 * @author Thinkpad
 * @date 2020年9月11日 下午2:36:01
 */
public class RepaymentServiceTest {

    private String repaymentId;

    private RepaymentRepository repaymentRepository;

    private Repayment repayment;

    @Before
    public void setUp() throws Exception {
        repaymentRepository = mock(RepaymentRepository.class);

        // GIVEN
        repaymentId = "1";
        repayment = new Repayment();
        repayment.setId(repaymentId);
        // when(repaymentRepository.findById(same(repaymentId))).thenReturn(AggregateFactory.createAggregate(repayment));
    }

    @Test
    public void SHOULD_FIND_REPAYMENT_WHEN_GIVE_ID() {
        // GIVEN
        // WHEN
        // THEN

        // WHEN
        when(repaymentRepository.findById(same(repaymentId))).thenReturn(AggregateFactory.createAggregate(repayment));

        // THEN
        Assert.assertEquals(repaymentId,repayment.getId());
    }

}
