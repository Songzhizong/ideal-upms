package com.zzsong.quarkus.upms.application;

import com.zzsong.quarkus.upms.domain.model.user.UserDo;
import com.zzsong.quarkus.upms.domain.model.user.UserRepository;
import com.zzsong.quarkus.upms.domain.model.user.args.CreateUserArgs;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author 宋志宗 on 2021/8/26
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserDo register(CreateUserArgs args) {
    return null;
  }
}
