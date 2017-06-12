package org.yunzhong.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yunzhong.service.biz.HistoryDataService;
import org.yunzhong.service.dao.HistoryDataDAO;
import org.yunzhong.service.model.HistoryData;
import org.yunzhong.service.model.HistoryDataStat;
import org.yunzhong.service.model.HistoryDataStat.HistoryDataCollection;

/**
 * @author yunzhong
 *
 */
@Service
@Transactional
public class HistoryDataServiceImpl implements HistoryDataService {
	private static final Logger log = LoggerFactory.getLogger(HistoryDataServiceImpl.class);

	@Autowired
	private HistoryDataDAO historyDao;

	@Override
	public int batchInsert(List<HistoryData> datas) {
		log.debug("batch insert history info");
		return historyDao.batchInsert(datas);
	}

	@Override
	public Map<Integer, HistoryDataStat> stat(String dataId) {
		Map<Integer, HistoryDataStat> result = new HashMap<>();
		List<HistoryData> datas = historyDao.selectById(dataId);
		if (!CollectionUtils.isEmpty(datas)) {
			List<HistoryData> cacheData = new ArrayList<>();
			Integer cacheCount = 0;
			HistoryData old = null;
			Date startDate = null;
			Date endDate = null;
			for (HistoryData data : datas) {
				if(startDate == null){
					startDate = data.getDate();
				}
				if (old == null) {
					old = data;
					continue;
				}
				if (old.getClose() < data.getClose()) {// 涨
					cacheCount++;
					cacheData.add(data);
					old = data;
					endDate = data.getDate();
				} else {// 未涨
					old = data;
					if (cacheCount <= 1) { // 没有连涨
					} else { // 有连涨，记录历史
						HistoryDataStat resultC = result.get(cacheCount);
						if (resultC == null) {
							resultC = new HistoryDataStat();
							result.put(cacheCount, resultC);
						}
						HistoryDataCollection collection = new HistoryDataCollection();
						collection.setDatas(cacheData);
						collection.setStart(startDate);
						collection.setEnd(endDate);
						resultC.getDataCollection().add(collection);
					}
					cacheCount = 0;
					cacheData = new ArrayList<>();
					startDate = null;
				}
			}
		}
		return result;
	}

	@Override
	public Map<Integer, HistoryDataStat> statUpStaying(String dataId) {
		Map<Integer, HistoryDataStat> result = new HashMap<>();
		List<HistoryData> datas = historyDao.selectById(dataId);
		if (!CollectionUtils.isEmpty(datas)) {
			List<HistoryData> cacheData = new ArrayList<>();
			Integer cacheCount = 0;
			HistoryData old = null;
			Date startDate = null;
			Date endDate = null;
			for (HistoryData data : datas) {
				if(startDate == null){
					startDate = data.getDate();
				}
				if (old == null) {
					old = data;
					continue;
				}
				if (old.getClose() < data.getClose()) {// 涨停
					cacheCount++;
					cacheData.add(data);
					old = data;
					endDate = data.getDate();
				} else {// 未涨停
					old = data;
					if (cacheCount <= 1) { // 没有连涨
					} else { // 有连涨，记录历史
						HistoryDataStat resultC = result.get(cacheCount);
						if (resultC == null) {
							resultC = new HistoryDataStat();
							result.put(cacheCount, resultC);
						}
						HistoryDataCollection collection = new HistoryDataCollection();
						collection.setDatas(cacheData);
						collection.setStart(startDate);
						collection.setEnd(endDate);
						resultC.getDataCollection().add(collection);
					}
					cacheCount = 0;
					cacheData = new ArrayList<>();
					startDate = null;
				}
			}
		}
		return result;
	}

}
