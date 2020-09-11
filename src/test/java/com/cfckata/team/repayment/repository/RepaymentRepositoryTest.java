/**
 * @title RepaymentRepositoryTest.java
 * @author Thinkpad
 * @date 2020年9月11日 下午3:21:18
 * @copyright 2020 中原消费金融有限公司版权所有
 */
package com.cfckata.team.repayment.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.cfckata.common.JsonComparator;
import com.cfckata.common.RepositoryTest;
import com.cfckata.team.repayment.domain.Repayment;
import com.github.meixuesong.aggregatepersistence.Aggregate;

/**
 * @Description <描述>
 * @author Thinkpad
 * @date 2020年9月11日 下午3:21:18
 */
public class RepaymentRepositoryTest extends RepositoryTest{

    private String repaymentId;
    
    @Autowired
    private RepaymentRepository repaymentRepository;
    
    @Before
    public void setUp() throws Exception {
        repaymentId="TEST_REPAYMENT";
    }
    @Test
    @Sql(scripts = "classpath:sql/repayment-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/repayment-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_find_repayment() throws ParseException {
        Aggregate<Repayment> repaymentAggregate = repaymentRepository.findById(repaymentId);
        Repayment repayment = createNormalTestRepayment(repaymentId);
        JsonComparator.assertEqualsObjects(repayment, repaymentAggregate.getRoot());
    }
    
    private Repayment createNormalTestRepayment(String repaymentId) throws ParseException {
        Repayment repayment=new Repayment();
        repayment.setId(repaymentId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = "2019-09-20"; 
        Date date = formatter.parse(s);
        //2019-09-20
        repayment.setCreateTime(date);
        repayment.setVersion(1);
        return repayment;
    }
    
    
}
