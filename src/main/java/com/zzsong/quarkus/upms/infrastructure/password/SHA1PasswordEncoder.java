package com.zzsong.quarkus.upms.infrastructure.password;

import cn.idealframework.crypto.SHA1;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2021/7/5
 */
@RequiredArgsConstructor
public class SHA1PasswordEncoder implements PasswordEncoder {
  private final String salt;

  @Override
  public String encode(@Nonnull CharSequence rawPassword) {
    return encryptPwd(rawPassword.toString());
  }

  @Override
  public boolean matches(@Nonnull CharSequence rawPassword,
                         @Nonnull String encodedPassword) {
    return encryptPwd(rawPassword.toString()).equals(encodedPassword);
  }

  @Nonnull
  private String encryptPwd(@Nonnull String password) {
    String en1 = SHA1.encode(password + salt);
    String en2 = SHA1.encode(salt + en1);
    return SHA1.encode(en1 + salt + en2);
  }
}
