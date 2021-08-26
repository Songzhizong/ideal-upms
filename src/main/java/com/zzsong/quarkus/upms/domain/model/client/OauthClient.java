package com.zzsong.quarkus.upms.domain.model.client;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 宋志宗 on 2021/7/15
 */
@Getter
@Setter
public class OauthClient {
  private long id;

  private String platform;

  private boolean multiTenant;

  private String clientId;

//  private String resourceIds;

//  private String clientSecret;

  private String scope;

//  private String authorizedGrantTypes;

//  private String webServerRedirectUri;

//  private String authorities;

  private int accessTokenValidity;

  private boolean accessTokenAutoRenewal;

  private int refreshTokenValidity;

//  private String additionalInformation;

//  private String autoapprove;

  private String description;
}
