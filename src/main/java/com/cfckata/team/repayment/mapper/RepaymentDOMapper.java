package com.cfckata.team.repayment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cfckata.team.repayment.dao.RepaymentDO;

@Mapper
@Repository
public interface RepaymentDOMapper {

    RepaymentDO selectByPrimaryKey(String id);
}
