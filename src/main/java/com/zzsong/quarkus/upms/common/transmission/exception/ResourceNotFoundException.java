package com.zzsong.quarkus.upms.common.transmission.exception;

import com.zzsong.quarkus.upms.common.transmission.CommonResMsg;

import javax.annotation.Nonnull;

/**
 * 资源不存在异常, 如果要查询的某个资源不存在则抛出此异常
 *
 * @author 宋志宗 on 2020/12/13
 */
public class ResourceNotFoundException extends VisibleRuntimeException {
  private static final long serialVersionUID = 1793885115376476276L;

  public ResourceNotFoundException() {
    this(CommonResMsg.NOT_FOUND.message());
  }

  public ResourceNotFoundException(@Nonnull String message) {
    super(CommonResMsg.NOT_FOUND.code(), message);
  }
}
