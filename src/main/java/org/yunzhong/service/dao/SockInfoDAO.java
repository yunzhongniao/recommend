package org.yunzhong.service.dao;

import org.apache.ibatis.annotations.Param;
import org.yunzhong.service.model.SockInfo;

public interface SockInfoDAO extends BaseDAO {

	/**
	 * @param sockInfo
	 * @return
	 */
	int insert(@Param("sockInfo")SockInfo sockInfo);
}
