package org.yunzhong.service.biz;

import java.util.List;
import java.util.Map;

import org.yunzhong.controller.param.StatParam;
import org.yunzhong.service.model.HistoryData;
import org.yunzhong.service.model.HistoryDataPreStat;
import org.yunzhong.service.model.HistoryDataStat;

public interface HistoryDataService {

    /**
     * @param datas
     * @return
     */
    int batchInsert(List<HistoryData> datas);

    /**
     * 连涨统计
     * 
     * @param dataId
     * @return
     */
    Map<Integer, HistoryDataStat> stat(String dataId);

    /**
     * 涨停统计
     * 
     * @param dataId
     * @return
     */
    Map<Integer, HistoryDataStat> statUpStaying(String dataId, Double percentage);

    /**
     * 连续涨之后，下一天的涨跌统计
     * 
     * @param param
     * @return
     */
    HistoryDataPreStat preUpRate(StatParam param);

    /**
     * 连续涨之后，下一天的涨跌统计. 如果连续涨次数超过count，则本次连续涨只计算一次。
     * 
     * @param param
     * @return
     */
    public HistoryDataPreStat preUpRateOnce(StatParam param);
}
