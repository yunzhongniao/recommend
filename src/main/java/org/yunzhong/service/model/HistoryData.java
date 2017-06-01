package org.yunzhong.service.model;

import java.util.Date;

public class HistoryData extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 5140579215270768869L;

    private String id;
    private Date date;
    private Double open;
    private Double max;
    private Double close;
    private Long dealCount;
    private Double dealValue;
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getOpen() {
        return open;
    }
    public void setOpen(Double open) {
        this.open = open;
    }
    public Double getMax() {
        return max;
    }
    public void setMax(Double max) {
        this.max = max;
    }
    public Double getClose() {
        return close;
    }
    public void setClose(Double close) {
        this.close = close;
    }
    public Long getDealCount() {
        return dealCount;
    }
    public void setDealCount(Long dealCount) {
        this.dealCount = dealCount;
    }
    public Double getDealValue() {
        return dealValue;
    }
    public void setDealValue(Double dealValue) {
        this.dealValue = dealValue;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
