package com.czh.cloud.sybyl.common.util;

import com.czh.cloud.common.util.CommonUtil;
import com.czh.cloud.sybyl.common.constants.SybylConst;
import com.czh.cloud.sybyl.common.enums.SybylEnum;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 14:04
 */
public class SybylUtil {
    private static Logger logger = LoggerFactory.getLogger(SybylUtil.class);

    public static Map<String, Recommender> map = new HashMap<>();

    public static Recommender getRecommender(int kind) {
        SybylEnum sybylEnum = SybylEnum.get(kind);
        Recommender recommender = map.get(sybylEnum.getContent());
        if (recommender == null) {
            try {
                //准备数据 这里是电影评分数据
                File file = getFile(sybylEnum);
                //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
                DataModel dataModel = null;
                dataModel = new FileDataModel(file);
                //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
//                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
                //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
                UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
                //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
                recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
                map.put(sybylEnum.getContent(), recommender);
            } catch (Exception e) {
                logger.error("加载mahout文件信息异常", e);
            }
        }
        return recommender;
    }

    public static File getFile(SybylEnum sybylEnum) {
        Date date = CommonUtil.getCurrentDate();
        File file;
        int i = 0;
        while (true) {
            i++;
            file = new File(SybylConst.mahoutPath + CommonUtil.getFormatDate(date, CommonUtil.YYYYMMDD) + "\\" + sybylEnum.getContent());
            if (file.exists()) {
                break;
            }
            if (i > 10) {
                break;
            }
            date = CommonUtil.changeDate(date, -1, Calendar.DAY_OF_YEAR);
        }
        return file;
    }
}
