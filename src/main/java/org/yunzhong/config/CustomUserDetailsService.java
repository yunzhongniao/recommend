package org.yunzhong.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yunzhong.service.biz.AccountService;
import org.yunzhong.service.model.Account;
import org.yunzhong.util.ApplicationContextFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yunzhong on 2017/4/20.
 */
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        log.info("当前的用户名是：" + token.getName());
        String account = token.getName();
        Account user = ApplicationContextFactory.getBean(AccountService.class).getByAccount(account);
        if(user == null) {
            log.error("Failed to get user [{0}] info.", account);
            throw new UsernameNotFoundException(account);
        }
        /*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getAccount());
        userInfo.setName(user.getName());
        Set<AuthorityInfo> authorities = new HashSet<>();
        AuthorityInfo authorityInfo = new AuthorityInfo(user.getRole());
        authorities.add(authorityInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}


