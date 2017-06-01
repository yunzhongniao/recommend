package org.yunzhong.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yunzhong.service.biz.AccountService;
import org.yunzhong.service.dao.AccountDAO;

/**
 * Created by yunzhong on 2017/4/20.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

}
