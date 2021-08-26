package com.zzsong.quarkus.upms.domain.model.client.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

/**
 * @author 宋志宗 on 2021/7/15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOauthClientArgs {
  /**
   * @required
   */
  private String clientId;

  /**
   * @required
   */
  private String clientSecret;

  @Nullable
  private String scope;

  /**
   * access token有效期, 默认1小时
   */
  @Nullable
  private Integer accessTokenValidity;

  /** access token是否开启自动续期 */
  private boolean accessTokenAutoRenewal;

  /**
   * refresh token有效期, 默认1天
   */
  @Nullable
  private Integer refreshTokenValidity;

  @Nullable
  private String description;
}
