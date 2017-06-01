package org.yunzhong.service.biz;

import java.util.List;

import org.yunzhong.service.model.HistoryData;

public interface HistoryDataService {

    /**
     * @param datas
     * @return
     */
    int batchInsert(List<HistoryData> datas);
}
