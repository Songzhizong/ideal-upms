package com.zzsong.quarkus.upms.domain.model.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author 宋志宗 on 2021/8/26
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<UserDo> {

  public Optional<UserDo> findByAccount(@Nonnull String account) {
    return find("account", UserDo.encrypt(account)).firstResultOptional();
  }

  public Optional<UserDo> findByMobile(@Nonnull String mobile) {
    return find("mobile", UserDo.encrypt(mobile)).firstResultOptional();
  }

  public Optional<UserDo> findByEmail(@Nonnull String email) {
    return find("email", UserDo.encrypt(email)).firstResultOptional();
  }

  public Optional<UserDo> findByCertificate(@Nonnull CertificateEnum certificate,
                                            @Nonnull String certificateNO) {
    String query = "certificate = ?1 and certificateNO = ?2";
    return find(query, certificate, UserDo.encrypt(certificateNO)).firstResultOptional();
  }

  public Optional<UserDo> findByCredentials(@Nonnull String credentials) {
    String query = "account = ?1 or mobile = ?2 or email = ?3";
    String encrypt = UserDo.encrypt(credentials);
    return find(query, encrypt, encrypt, encrypt).firstResultOptional();
  }
}
