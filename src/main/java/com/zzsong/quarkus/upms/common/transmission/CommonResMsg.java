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
package com.zzsong.quarkus.upms.common.transmission;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2020/10/23
 */
@Getter
@AllArgsConstructor
public enum CommonResMsg implements ResMsg {
  SUCCESS(200, "Success"),
  BAD_REQUEST(400, "Bad request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  ;

  private final int code;
  @Nonnull
  private final String message;

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
