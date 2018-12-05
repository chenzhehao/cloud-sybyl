package com.czh.cloud.sybyl.server.mapper;

import com.czh.cloud.sybyl.server.dao.PDoctor;

public interface PDoctorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PDoctor record);

    int insertSelective(PDoctor record);

    PDoctor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PDoctor record);

    int updateByPrimaryKey(PDoctor record);
}