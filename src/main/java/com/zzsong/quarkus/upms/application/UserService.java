package com.zzsong.quarkus.upms.application;

import cn.idealframework.lang.StringUtils;
import cn.idealframework.log.Logger;
import cn.idealframework.log.LoggerFactory;
import cn.idealframework.util.Asserts;
import com.zzsong.quarkus.upms.domain.model.user.CertificateEnum;
import com.zzsong.quarkus.upms.domain.model.user.UserDo;
import com.zzsong.quarkus.upms.domain.model.user.UserRepository;
import com.zzsong.quarkus.upms.domain.model.user.args.CreateUserArgs;
import com.zzsong.quarkus.upms.domain.model.user.exception.UserAuthenticationException;
import com.zzsong.quarkus.upms.infrastructure.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author 宋志宗 on 2021/8/26
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UserService {
  private static final Logger log = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * 注册账号
   *
   * @param args 注册信息
   * @return 用户信息
   * @author 宋志宗 on 2021/8/26
   */
  @Nonnull
  @Transactional(rollbackOn = Throwable.class)
  public UserDo register(@Nonnull CreateUserArgs args) {
    String account = args.getAccount();
    String mobile = args.getMobile();
    String email = args.getEmail();
    CertificateEnum certificate = args.getCertificate();
    String certificateNO = args.getCertificateNO();
    String password = args.getPassword();
    Asserts.notBlank(password, "密码不能为空");
    if (StringUtils.isNotBlank(account)) {
      Optional<UserDo> optional = userRepository.findByAccount(account);
      Asserts.assertFalse(optional.isPresent(), "账号已存在");
    }
    if (StringUtils.isNotBlank(mobile)) {
      Optional<UserDo> optional = userRepository.findByMobile(mobile);
      Asserts.assertFalse(optional.isPresent(), "手机号已存在");
    }
    if (StringUtils.isNotBlank(email)) {
      Optional<UserDo> optional = userRepository.findByEmail(email);
      Asserts.assertFalse(optional.isPresent(), "邮箱已存在");
    }
    if (certificate != null
        && certificate != CertificateEnum.NONE
        && StringUtils.isNotBlank(certificateNO)) {
      Optional<UserDo> optional = userRepository.findByCertificate(certificate, certificateNO);
      Asserts.assertFalse(optional.isPresent(), "证件号已被使用");
    }
    String encode = passwordEncoder.encode(password);
    args.setPassword(encode);
    UserDo userDo = UserDo.create(args);
    userRepository.persistAndFlush(userDo);
    return userDo;
  }

  /**
   * 通过唯一凭证+密码的方式进行身份验证
   *
   * @param credentials 唯一凭证 account | mobile | email
   * @param password    密码
   */
  public UserDo authenticate(@Nonnull String credentials,
                             @Nonnull String password) {
    UserDo userDo = userRepository.findByCredentials(credentials)
        .orElseThrow(() -> {
          log.info("通过唯一凭证获取用户信息为空, credentials: {}", credentials);
          return new UserAuthenticationException("用户名或密码错误");
        });
    String encodedPassword = userDo.getPassword();
    boolean matches = passwordEncoder.matches(password, encodedPassword);
    if (!matches) {
      log.info("用户认证失败, 输入密码错误");
      throw new UserAuthenticationException("用户名或密码错误");
    }
    log.info("成功通过用户唯一凭证认证, userId = {}", userDo.getId());
    return userDo;
  }
}
