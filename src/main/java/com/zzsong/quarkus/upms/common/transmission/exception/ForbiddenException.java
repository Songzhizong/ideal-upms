package com.zzsong.quarkus.upms.common.transmission.exception;

import com.zzsong.quarkus.upms.common.transmission.CommonResMsg;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2021/7/17
 */
public class ForbiddenException extends VisibleRuntimeException {
  private static final long serialVersionUID = 6175679034444446330L;
  public static final ForbiddenException INSTANCE = new ForbiddenException();

  public ForbiddenException() {
    super(403, CommonResMsg.FORBIDDEN.code(), CommonResMsg.FORBIDDEN.message());
  }


  public ForbiddenException(int code, @Nonnull String message) {
    super(403, code, message);
  }
}
