package org.yunzhong.service.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yunzhong
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SockInfo extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4952144171002719595L;

	private String id;
	private String name;
}
