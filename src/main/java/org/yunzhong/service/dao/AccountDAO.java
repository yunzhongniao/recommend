package org.yunzhong.service.dao;

import org.apache.ibatis.annotations.Param;
import org.yunzhong.service.model.Account;

/**
 * Created by yunzhong on 2017/4/20.
 */
public interface AccountDAO extends  BaseDAO {
    
    
    /**
     *
     * @param: account
     * @author: yunzhong
     * @Date: 2017/4/21 13:53
     */ 
    Account getByAccount(@Param("account") String account);
}
