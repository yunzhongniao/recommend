package org.yunzhong.service.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yunzhong.service.biz.HistoryDataService;
import org.yunzhong.service.dao.HistoryDataDAO;
import org.yunzhong.service.model.HistoryData;

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

}
