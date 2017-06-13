package org.yunzhong.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yunzhong.service.biz.HistoryDataService;
import org.yunzhong.service.model.HistoryDataStat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/history")
@Api(value = "股票交易历史")
@Slf4j
public class HistoryDataController {

	@Autowired
	private HistoryDataService historyService;

	/**
	 * @param dataId
	 * @return
	 */
	@ApiOperation(value = "获得单支股票的统计信息")
	@RequestMapping(value = "{dataId}/stat/single", method = RequestMethod.GET)
	public Map<Integer, HistoryDataStat> statSingle(@PathVariable String dataId) {
		log.info("get sock [" + dataId + "] stats info.");
		return historyService.stat(dataId);
	}

	/**
	 * @param dataId
	 * @param count
	 * @return
	 */
	@ApiOperation(value = "获得单支股票,固定连涨天数的统计信息")
	@RequestMapping(value = "{dataId}/stat/single/{count}", method = RequestMethod.GET)
	public HistoryDataStat statSingle(@PathVariable String dataId, @PathVariable Integer count) {
		log.info("get sock [" + dataId + "] stats info with count [" + count + "].");
		Map<Integer, HistoryDataStat> statMap = historyService.stat(dataId);
		if (statMap.containsKey(count)) {
			return statMap.get(count);
		}
		return new HistoryDataStat();
	}

	/**
	 * @param dataId
	 * @param count
	 * @return
	 */
	@ApiOperation(value = "获得单支股票连续涨停的统计信息")
	@RequestMapping(value = "{dataId}/{percentage}/stat/single", method = RequestMethod.GET)
	public Map<Integer, HistoryDataStat> statUpStaySingle(@PathVariable String dataId,
			@PathVariable(required = false) Double percentage) {
		log.info("get sock [" + dataId + "] up staying.");
		return historyService.statUpStaying(dataId, percentage);
	}

	/**
	 * @param dataId
	 * @param count
	 * @return
	 */
	@ApiOperation(value = "获得单支股票连续涨停的数据统计信息")
	@RequestMapping(value = "{dataId}/{percentage}/stat/single/simple", method = RequestMethod.GET)
	public Map<Integer, Integer> statUpStaySingleSimple(@PathVariable String dataId,
			@PathVariable(required = false) Double percentage) {
		log.info("get sock [" + dataId + "] up staying simple.");
		Map<Integer, Integer> result = new HashMap<>();
		Map<Integer, HistoryDataStat> temp = historyService.statUpStaying(dataId, percentage);
		Iterator<Entry<Integer, HistoryDataStat>> iterator = temp.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, HistoryDataStat> next = iterator.next();
			result.put(next.getKey(), next.getValue().getUpCount());
		}
		return result;
	}
}
