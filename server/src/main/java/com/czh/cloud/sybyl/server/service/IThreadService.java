package com.czh.cloud.sybyl.server.service;

import com.czh.cloud.sybyl.outer.entity.response.SwaggerRep;
import com.czh.cloud.common.entity.RootResponse;

import java.util.concurrent.Future;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/9/11 15:49
 */
public interface IThreadService {

    String threadTest0();

    Future<String> threadTest1() throws InterruptedException;

    RootResponse<String> threadTest2();

    String threadTest3();
}
