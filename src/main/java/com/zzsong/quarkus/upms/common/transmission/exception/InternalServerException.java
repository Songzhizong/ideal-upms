package com.zzsong.quarkus.upms.common.transmission.exception;

import com.zzsong.quarkus.upms.common.transmission.CommonResMsg;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2020/12/20
 */
public class InternalServerException extends VisibleRuntimeException {
  private static final long serialVersionUID = -8902215592749726908L;

  public InternalServerException(@Nonnull String message) {
    super(CommonResMsg.INTERNAL_SERVER_ERROR.code(), message);
  }
}
