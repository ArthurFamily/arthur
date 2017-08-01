package com.cloud.arthur.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by chenzhen on 2017/7/28.
 */
@Data
public class Users {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    private String password;
}
