package com.czh.cloud.sybyl.common.enums;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/11/29 14:07
 */
public enum SybylEnum {

    kind_1(1, "course.csv", "课程"),
    kind_2(2, "film.dat", "电影");


    private int kind;
    private String content;
    private String explain;

    SybylEnum(int kind, String content, String explain) {
        this.kind = kind;
        this.content = content;
        this.explain = explain;
    }

    public int getKind() {
        return this.kind;
    }

    public String getContent() {
        return this.content;
    }

    public String getExplain() {
        return this.explain;
    }

    public static SybylEnum get(int kind) {
        SybylEnum[] sybylEnums = values();
        for (SybylEnum item : sybylEnums) {
            if (item.kind == kind) {
                return item;
            }
        }
        return null;
    }

}
