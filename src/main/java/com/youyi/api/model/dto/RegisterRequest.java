package com.youyi.api.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaozhi
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Length(min = 6, max = 20, message = "密码长度为6-16位")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Length(min = 6, max = 20, message = "确认密码长度为6-16位")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
