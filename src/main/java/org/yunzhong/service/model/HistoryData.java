package org.yunzhong.service.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryData extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 5140579215270768869L;

    private String id;
    private Date date;
    private Double open;
    private Double max;
    private Double min;
    private Double close;
    private Long dealCount;
    private Double dealValue;
    
    
}
