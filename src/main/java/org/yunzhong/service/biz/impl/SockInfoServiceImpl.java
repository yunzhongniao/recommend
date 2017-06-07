package org.yunzhong.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunzhong.service.biz.SockInfoService;
import org.yunzhong.service.dao.SockInfoDAO;
import org.yunzhong.service.model.SockInfo;

/**
 * @author yunzhong
 *
 */
@Service
public class SockInfoServiceImpl implements SockInfoService {

	@Autowired
	private SockInfoDAO sockInfoDao;

	@Override
	public int insert(SockInfo sockInfo) {
		if (sockInfo == null) {
			return 0;
		}
		return sockInfoDao.insert(sockInfo);
	}

}
