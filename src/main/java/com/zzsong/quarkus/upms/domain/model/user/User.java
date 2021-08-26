package com.zzsong.quarkus.upms.domain.model.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 宋志宗 on 2021/8/26
 */
@Getter
@Setter
public class User {
  private long id;

  private String name;

  private String account;

  private String mobile;

  private String email;

  private GenderEnum gender;

  private CertificateEnum certificate;

  private String certificateNO;

  private LocalDate birthday;

  private String profilePhoto;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;
}
