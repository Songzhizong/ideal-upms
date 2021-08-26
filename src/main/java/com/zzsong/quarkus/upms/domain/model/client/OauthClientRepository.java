package com.zzsong.quarkus.upms.domain.model.client;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author 宋志宗 on 2021/8/26
 */
@ApplicationScoped
public class OauthClientRepository implements PanacheRepository<OauthClientDo> {

  @Nonnull
  Optional<OauthClientDo> findByClientId(@Nonnull String clientId) {
    return find("clientId", clientId).firstResultOptional();
  }
}
