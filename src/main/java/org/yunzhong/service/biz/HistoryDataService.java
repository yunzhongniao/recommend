package org.yunzhong.service.biz;

import java.util.List;
import java.util.Map;

import org.yunzhong.service.model.HistoryData;
import org.yunzhong.service.model.HistoryDataStat;

public interface HistoryDataService {

    /**
     * @param datas
     * @return
     */
    int batchInsert(List<HistoryData> datas);

	/**
	 * @param dataId
	 * @return
	 */
    Map<Integer, HistoryDataStat> stat(String dataId);
}
