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

import com.zzsong.quarkus.upms.common.transmission.CommonResMsg;

import javax.annotation.Nonnull;

/**
 * 未经授权异常, 如果访问的某个资源没有经过授权, 则会抛出此异常
 *
 * @author 宋志宗 on 2020/12/13
 */
public class UnauthorizedException extends VisibleRuntimeException {
  private static final long serialVersionUID = -6919612166500234216L;
  public static UnauthorizedException INSTANCE = new UnauthorizedException();

  public UnauthorizedException() {
    super(401, CommonResMsg.UNAUTHORIZED.code(), CommonResMsg.UNAUTHORIZED.message());
  }


  public UnauthorizedException(int code, @Nonnull String message) {
    super(401, code, message);
  }
}
