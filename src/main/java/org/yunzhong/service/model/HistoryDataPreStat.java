package org.yunzhong.service.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryDataPreStat extends BaseModel {
	/**
	* 
	*/
	private static final long serialVersionUID = -2949985914450173919L;

	/**
	 * 连涨天数
	 */
	private Integer upStayCount;
	/**
	 * 连涨的涨幅
	 */
	private Double percentage;
	/**
	 * 股票id
	 */
	private String dataId;

	/**
	 * upStayCount 的次数
	 */
	private Integer totalCount = 0;
	/**
	 * upStayCount 之后，依旧涨的次数
	 */
	private Integer upCount = 0;
	/**
	 * upStayCount之后，跌的次数
	 */
	private Integer downCount = 0;
	/**
	 * upStayCount之后，持平的次数
	 */
	private Integer unchangeCount = 0;

	public void totalCountPlus() {
		this.totalCount++;
	}

	public void upCountPlus() {
		this.upCount++;
	}

	public void downCountPlus() {
		this.downCount++;
	}

	public void unchangeCountPlus() {
		this.unchangeCount++;
	}
}
