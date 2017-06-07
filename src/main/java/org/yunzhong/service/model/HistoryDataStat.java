package org.yunzhong.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryDataStat extends BaseModel {
	/**
	* 
	*/
	private static final long serialVersionUID = -6634682167043972520L;

	@Data
	public static class HistoryDataCollection {
		/**
		 * 连涨开始日期
		 */
		private Date start;
		/**
		 * 连涨结束日期
		 */
		private Date end;
		/**
		 * 连涨区段列表
		 */
		private List<HistoryData> datas;

	}

	/**
	 * 连涨天数
	 */
	private Integer count;

	/**
	 * 符合此连涨天数的区段列表
	 */
	List<HistoryDataCollection> dataCollection = new ArrayList<>();

}
