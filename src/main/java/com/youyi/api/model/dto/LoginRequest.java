package com.youyi.api.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaozhi
 */
@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度为6-16位")
    private String password;
}
