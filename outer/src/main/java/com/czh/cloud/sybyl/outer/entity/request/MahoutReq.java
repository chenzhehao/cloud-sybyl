package com.czh.cloud.sybyl.outer.entity.request;

import com.czh.cloud.common.entity.RootReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 11:27
 */
@ApiModel(value = "SwaggerReq对象", description = "样例对象")
public class MahoutReq extends RootReq {

    @ApiModelProperty(name = "kind", value = "查询推荐种类", example = "1,2")
    @NotNull(message = "查询推荐种类不能为空")
    private int kind;

    @ApiModelProperty(name = "id", value = "推荐人id或推荐物id", example = "123")
    @Range(min = 1)
    private int id;

    @ApiModelProperty(name = "pageNo", value = "页面号", example = "1")
    @Range(min = 1)
    private int pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数", example = "10")
    @Range(min = 1)
    private int pageSize;

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
