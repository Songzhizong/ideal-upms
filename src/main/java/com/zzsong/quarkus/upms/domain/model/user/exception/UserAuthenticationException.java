package com.zzsong.quarkus.upms.domain.model.user.exception;

import cn.idealframework.transmission.exception.VisibleRuntimeException;

import javax.annotation.Nonnull;

/**
 * 用户认证异常
 *
 * @author 宋志宗 on 2021/8/26
 */
public class UserAuthenticationException extends VisibleRuntimeException {
  private static final long serialVersionUID = -2244944227093546298L;

  public UserAuthenticationException(@Nonnull String message) {
    super(400, message);
  }
}
