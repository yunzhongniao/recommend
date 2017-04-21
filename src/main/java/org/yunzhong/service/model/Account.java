package org.yunzhong.service.model;

import lombok.Data;

/**
 * Created by yunzhong on 2017/4/20.
 */
@Data
public class Account extends BaseModel {
    private Long id;
    private String name;
    private String account;
    private String password;
    private String role;

}
