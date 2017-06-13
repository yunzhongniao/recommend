package org.yunzhong.service.biz.impl;

import java.text.SimpleDateFormat;
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
import org.yunzhong.controller.param.StatParam;
import org.yunzhong.service.biz.HistoryDataService;
import org.yunzhong.service.dao.HistoryDataDAO;
import org.yunzhong.service.model.HistoryData;
import org.yunzhong.service.model.HistoryDataPreStat;
import org.yunzhong.service.model.HistoryDataStat;
import org.yunzhong.service.model.HistoryDataStat.HistoryDataCollection;
import org.yunzhong.util.SockUtil;

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
				if (startDate == null) {
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
	public Map<Integer, HistoryDataStat> statUpStaying(String dataId, Double percentage) {
		Map<Integer, HistoryDataStat> result = new HashMap<>();
		List<HistoryData> datas = historyDao.selectById(dataId);
		if (!CollectionUtils.isEmpty(datas)) {
			List<HistoryData> cacheData = new ArrayList<>();
			Integer cacheCount = 0;
			HistoryData old = null;
			Date startDate = null;
			Date endDate = null;
			for (HistoryData data : datas) {
				if (startDate == null) {
					startDate = data.getDate();
				}
				if (old == null) {
					old = data;
					continue;
				}
				if (SockUtil.upStaying(old.getClose(), data.getClose(), percentage)) {// 涨停
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
							resultC.setCount(cacheCount);
							result.put(cacheCount, resultC);
						}
						HistoryDataCollection collection = new HistoryDataCollection();

						collection.setDatas(cacheData);
						collection.setStart(startDate);
						collection.setEnd(endDate);
						resultC.getDataCollection().add(collection);
						resultC.setUpCount(resultC.getDataCollection().size());
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
	public HistoryDataPreStat preUpRate(StatParam param) {
		List<HistoryData> datas = historyDao.selectById(param.getDataId());
		HistoryDataPreStat result = new HistoryDataPreStat();
		result.setDataId(param.getDataId());
		result.setPercentage(param.getPercentage());
		result.setUpStayCount(param.getUpStayCount());

		if (CollectionUtils.isEmpty(datas)) {
			return result;
		}

		int count = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (HistoryData data : datas) {
			if (data.getUpRate() == null) {
				continue;
			}
			if (count == param.getUpStayCount()) { // 连涨次数刚好，看当前的data是跌还是涨。
				log.info("date: " + formatter.format(data.getDate()) + " | uprate: " + data.getUpRate());
				result.totalCountPlus();
				if (data.getUpRate() > 0) {
					result.upCountPlus();
				} else if (data.getUpRate() < 0) {
					result.downCountPlus();
				} else {
					result.unchangeCountPlus();
				}
				if (data.getUpRate() >= param.getPercentage()) {
					count++;
				} else {
					count = 0;
				}
			} else {// 如果连涨的次数少于目标，则继续累计；如果连涨的次数多余目标，继续向下查看，直到结束本次连涨。
				if (data.getUpRate() >= param.getPercentage()) {
					count++;
				} else {
					count = 0;
				}
			}

		}
		return result;
	}

}
