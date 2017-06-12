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
	Map<Integer, HistoryDataStat> statUpStaying(String dataId);
}
