package cn.redblood.config;

import cn.redblood.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 */
public class BoundSql {

    //解析后的sql
    private String sqlText;

    public String getSqlText() {
        return sqlText;
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();
}
