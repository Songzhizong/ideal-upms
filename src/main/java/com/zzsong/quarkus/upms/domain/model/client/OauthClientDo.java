package com.zzsong.quarkus.upms.domain.model.client;

import cn.idealframework.lang.StringUtils;
import com.zzsong.quarkus.upms.domain.model.client.args.CreateOauthClientArgs;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * @author 宋志宗 on 2021/8/26
 */
@SuppressWarnings({"SameParameterValue", "SpellCheckingInspection"})
@Entity
@Table(name = OauthClientDo.ENTITY_NAME,
    indexes = {
        @Index(name = "uk_client_id", columnList = "clientId", unique = true),
    })
@org.hibernate.annotations.Table(appliesTo = OauthClientDo.ENTITY_NAME, comment = "Oauth Client")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthClientDo {
  public static final String ENTITY_NAME = "upms_oauth_client";

  @Id
  @Nonnull
  @GeneratedValue(generator = "upms_oauth_client_generator")
  @GenericGenerator(name = "upms_oauth_client_generator",
      strategy = "cn.idealframework.id.JpaIDGenerator")
  @Column(nullable = false, updatable = false)
  private Long id;

  @Nonnull
  @Column(length = 48, nullable = false)
  private String clientId;

  @Nonnull
  @Column(nullable = false)
  private String resourceIds;

  @Nonnull
  @Column(nullable = false)
  private String clientSecret;

  @Nonnull
  @Column(nullable = false)
  private String scope;

  @Nonnull
  @Column(nullable = false)
  private String authorizedGrantTypes;

  @Nonnull
  @Column(nullable = false)
  private String webServerRedirectUri;

  @Nonnull
  @Column(nullable = false)
  private String authorities;

  @Column(nullable = false)
  private int accessTokenValidity;

  @Column(nullable = false)
  private int accessTokenAutoRenewal = 1;

  @Column(nullable = false)
  private int refreshTokenValidity;

  @Nonnull
  @Column(nullable = false, length = 4096)
  private String additionalInformation;

  @Nonnull
  @Column(nullable = false)
  private String autoapprove;

  @Nonnull
  @Column(nullable = false)
  private String description;

  @Nonnull
  public static OauthClientDo create(@Nonnull CreateOauthClientArgs args) {
    OauthClientDo clientDo = new OauthClientDo();
    clientDo.setClientId(args.getClientId());
    clientDo.setResourceIds(null);
    clientDo.setClientSecret(args.getClientSecret());
    clientDo.setScope(args.getScope());
    clientDo.setAuthorizedGrantTypes(null);
    clientDo.setWebServerRedirectUri(null);
    clientDo.setAuthorities(null);
    clientDo.setAccessTokenValidity(args.getAccessTokenValidity());
    clientDo.setAccessTokenAutoRenewal(args.isAccessTokenAutoRenewal() ? 1 : 0);
    clientDo.setRefreshTokenValidity(args.getRefreshTokenValidity());
    clientDo.setAdditionalInformation(null);
    clientDo.setAutoapprove(null);
    clientDo.setDescription(args.getDescription());
    return clientDo;
  }

  @Nonnull
  public OauthClient toOauthClient() {
    OauthClient oauthClient = new OauthClient();
    oauthClient.setId(this.getId());
    oauthClient.setClientId(this.getClientId());
//    oauthClient.setResourceIds(this.getResourceIds());
//    oauthClient.setClientSecret(this.getClientSecret());
    oauthClient.setScope(this.getScope());
//    oauthClient.setAuthorizedGrantTypes(this.getResourceIds());
//    oauthClient.setWebServerRedirectUri(this.getWebServerRedirectUri());
//    oauthClient.setAuthorities(this.getAuthorities());
    oauthClient.setAccessTokenValidity(this.getAccessTokenValidity());
    oauthClient.setAccessTokenAutoRenewal(this.accessTokenAutoRenewal());
    oauthClient.setRefreshTokenValidity(this.getRefreshTokenValidity());
//    oauthClient.setAdditionalInformation(this.getAdditionalInformation());
//    oauthClient.setAutoapprove(this.getAutoapprove());
    oauthClient.setDescription(this.getDescription());
    return oauthClient;
  }

  public boolean accessTokenAutoRenewal() {
    return this.getAccessTokenAutoRenewal() == 1;
  }


  protected void setResourceIds(@Nullable String resourceIds) {
    if (StringUtils.isBlank(resourceIds)) {
      resourceIds = "";
    }
    this.resourceIds = resourceIds;
  }

  protected void setScope(@Nullable String scope) {
    if (StringUtils.isBlank(scope)) {
      scope = "";
    }
    this.scope = scope;
  }

  protected void setAuthorizedGrantTypes(@Nullable String authorizedGrantTypes) {
    if (StringUtils.isBlank(authorizedGrantTypes)) {
      authorizedGrantTypes = "";
    }
    this.authorizedGrantTypes = authorizedGrantTypes;
  }

  protected void setWebServerRedirectUri(@Nullable String webServerRedirectUri) {
    if (StringUtils.isBlank(webServerRedirectUri)) {
      webServerRedirectUri = "";
    }
    this.webServerRedirectUri = webServerRedirectUri;
  }

  protected void setAuthorities(@Nullable String authorities) {
    if (StringUtils.isBlank(authorities)) {
      authorities = "";
    }
    this.authorities = authorities;
  }

  protected void setAccessTokenValidity(@Nullable Integer accessTokenValidity) {
    if (accessTokenValidity == null) {
      accessTokenValidity = 3600;
    }
    this.accessTokenValidity = Math.max(accessTokenValidity, 1800);
  }

  protected void setRefreshTokenValidity(@Nullable Integer refreshTokenValidity) {
    if (refreshTokenValidity == null) {
      refreshTokenValidity = 86400;
    }
    this.refreshTokenValidity = Math.max(refreshTokenValidity, 3600);
  }

  protected void setAdditionalInformation(@Nullable String additionalInformation) {
    if (StringUtils.isBlank(additionalInformation)) {
      additionalInformation = "";
    }
    this.additionalInformation = additionalInformation;
  }

  protected void setAutoapprove(@Nullable String autoapprove) {
    if (StringUtils.isBlank(autoapprove)) {
      autoapprove = "";
    }
    this.autoapprove = autoapprove;
  }

  protected void setDescription(@Nullable String description) {
    if (StringUtils.isBlank(description)) {
      description = "";
    }
    this.description = description;
  }
}
