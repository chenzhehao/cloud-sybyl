package com.czh.cloud.sybyl.server.service.impl;

import com.czh.cloud.sybyl.common.util.SybylUtil;
import com.czh.cloud.sybyl.outer.entity.request.MahoutReq;
import com.czh.cloud.sybyl.server.service.MahoutService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 11:36
 */
@Service
public class MahoutServiceImpl implements MahoutService {

    @Override
    public Map getList(MahoutReq mahoutReq) {
        Map resultMap = new HashMap();

        Recommender recommender = SybylUtil.getRecommender(mahoutReq.getKind());
        if (recommender == null) {

        } else {
            try {
                List<RecommendedItem> list = recommender.recommend(mahoutReq.getId(), mahoutReq.getPageNo() * mahoutReq.getPageSize());
                resultMap.put("list", list);
            } catch (TasteException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
}
