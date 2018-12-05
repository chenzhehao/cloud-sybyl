package com.czh.cloud.sybyl.server.controller;

import com.czh.cloud.common.controller.RootController;
import com.czh.cloud.common.entity.RootResponse;
import com.czh.cloud.sybyl.outer.entity.request.MahoutReq;
import com.czh.cloud.sybyl.server.service.MahoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 11:20
 */
@RestController
@RequestMapping("/mahout")
@Api(value = "mahout", description = "mahout推荐功能接口汇总")
public class MahoutController extends RootController {
    public static final Logger logger = LoggerFactory.getLogger(MahoutController.class);

    @Autowired
    MahoutService mahoutService;

    @ApiOperation(value = "基于推荐接口(无登录验证)", response = String.class, notes = "此处描述的返回对象为data内容，外层有code和message")
    @GetMapping(value = "/v1/list", produces = "application/json;charset=UTF-8")
    public RootResponse<Map> v1GetList(@Valid MahoutReq mahoutReq,
                                       final BindingResult bindingResult) {
        RootResponse<Map> rootResponse = RootResponse.instance();
        rootResponse.setData(mahoutService.getList(mahoutReq));
        return rootResponse;
    }
}
