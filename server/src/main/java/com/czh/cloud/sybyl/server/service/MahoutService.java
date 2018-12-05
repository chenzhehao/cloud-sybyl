package com.czh.cloud.sybyl.server.service;

import com.czh.cloud.sybyl.outer.entity.request.MahoutReq;

import java.util.Map;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 11:36
 */
public interface MahoutService {

    Map getList(MahoutReq mahoutReq);
}
