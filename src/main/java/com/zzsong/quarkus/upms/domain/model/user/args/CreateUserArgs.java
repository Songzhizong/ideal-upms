package com.zzsong.quarkus.upms.domain.model.user.args;

import com.zzsong.quarkus.upms.domain.model.user.CertificateEnum;
import com.zzsong.quarkus.upms.domain.model.user.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.time.LocalDate;

/**
 * @author 宋志宗 on 2021/8/26
 */
@Getter
@Setter
public class CreateUserArgs {
  /**
   * @required
   */
  private String name;

  /** 账号, 不能与手机号同时为空 */
  @Nullable
  private String account;

  /** 手机号, 不能与账号同时为空 */
  @Nullable
  private String mobile;

  @Nullable
  private String email;

  /**
   * @required
   */
  private String password;

  @Nullable
  private GenderEnum gender;

  @Nullable
  private CertificateEnum certificate;

  @Nullable
  private String certificateNO;

  @Nullable
  private LocalDate birthday;

  @Nullable
  private String profilePhoto;
}
