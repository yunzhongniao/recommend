package org.yunzhong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping("{dataId}/stat/single")
	public List<HistoryDataStat> stat(@PathVariable String dataId){
		log.info("get sock ["+dataId+"] stats info.");
		return historyService.stat(dataId);
	}
}
