package org.yunzhong.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yunzhong.service.model.HistoryData;

public interface HistoryDataDAO extends BaseDAO {

    /**
     * @param datas
     * @return
     */
    int batchInsert(@Param("datas") List<HistoryData> datas);

	/**
	 * @param dataId
	 * @return
	 */
	List<HistoryData> selectById(@Param("dataId")String dataId);
}
