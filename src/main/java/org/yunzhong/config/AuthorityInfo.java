package org.yunzhong.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yunzhong on 2017/4/20.
 */
public class AuthorityInfo implements GrantedAuthority {
	private static final long serialVersionUID = -384035727190448052L;
	/**
     * 权限CODE
     */
    private String authority;

    public AuthorityInfo(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
