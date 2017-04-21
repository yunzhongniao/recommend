package org.yunzhong.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        /*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setName("admin");
        Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
        AuthorityInfo authorityInfo = new AuthorityInfo("ADMIN");
        authorities.add(authorityInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}


