package com.zzsong.quarkus.upms.infrastructure.password;

import cn.idealframework.crypto.BCrypt;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2021/7/5
 */
public class BCryptPasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(@Nonnull CharSequence rawPassword) {
    String salt = BCrypt.gensalt();
    return BCrypt.hashpw(rawPassword.toString(), salt);
  }

  @Override
  public boolean matches(@Nonnull CharSequence rawPassword, @Nonnull String encodedPassword) {
    return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
  }
}
