package com.zzsong.quarkus.upms.common.transmission.exception;

import com.zzsong.quarkus.upms.common.transmission.CommonResMsg;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2020/12/20
 */
public class BadRequestException extends VisibleRuntimeException {
  private static final long serialVersionUID = 6232518534259102606L;

  public BadRequestException(@Nonnull String message) {
    super(CommonResMsg.BAD_REQUEST.code(), message);
  }
}
