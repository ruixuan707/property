package com.monco.core.query;

public class QueryParam {

    /**
     * 查询属性
     */
    private String filed;

    /**
     * 查询值(包装类型，如日期则Date,如Double,Integer, )
     */
    private Object value;

    /**
     * 查询连接符
     */
    private String connector = "and";

    /**
     * 操作枚举符
     */
    private MatchType matchType;

    public QueryParam(String filed, MatchType matchType, Object value,
                      String connector) {
        super();
        this.filed = filed;
        this.matchType = matchType;
        this.value = value;
        this.connector = connector;
    }

    public QueryParam(String filed, MatchType matchType, Object value) {
        super();
        this.filed = filed;
        this.matchType = matchType;
        this.value = value;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }
}
