/*
 * Copyright 2021 cn.idealframework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zzsong.quarkus.upms.common.transmission.exception;

import com.zzsong.quarkus.upms.common.transmission.ResMsg;
import com.zzsong.quarkus.upms.common.transmission.Result;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * 可作为响应内容的可见性运行时异常
 *
 * @author 宋志宗 on 2020/12/20
 */
@Getter
public class VisibleRuntimeException extends RuntimeException implements ResMsg {
  private static final long serialVersionUID = -2032153906053271110L;

  private final int httpStatus;

  private final int code;
  @Nonnull
  private final String message;

  public VisibleRuntimeException(@Nonnull ResMsg resMsg) {
    super(resMsg.message());
    this.httpStatus = resMsg.httpStatus();
    this.code = resMsg.code();
    this.message = resMsg.message();
  }

  public VisibleRuntimeException(@Nonnull Result<?> result) {
    this(200, result.getCode(), result.getMessage());
  }


  public VisibleRuntimeException(int code, @Nonnull String message) {
    this(200, code, message);
  }

  public VisibleRuntimeException(int httpStatus, int code, @Nonnull String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
    this.message = message;
  }

  @Override
  public int code() {
    return code;
  }

  @Nonnull
  @Override
  public String message() {
    return message;
  }
}
