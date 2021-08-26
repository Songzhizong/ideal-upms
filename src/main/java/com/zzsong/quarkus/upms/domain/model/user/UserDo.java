package com.zzsong.quarkus.upms.domain.model.user;

import cn.idealframework.crypto.AES;
import cn.idealframework.lang.StringUtils;
import cn.idealframework.util.Asserts;
import cn.idealframework.util.CheckUtils;
import com.zzsong.quarkus.upms.common.AesUtils;
import com.zzsong.quarkus.upms.domain.model.user.args.CreateUserArgs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 宋志宗 on 2021/8/26
 */
@Entity
@Table(name = UserDo.ENTITY_NAME,
    indexes = {
        @Index(name = "name", columnList = "name"),
        @Index(name = "uk_account", columnList = "account", unique = true),
        @Index(name = "uk_mobile", columnList = "mobile", unique = true),
        @Index(name = "uk_email", columnList = "email", unique = true),
        @Index(name = "uck_certificate", columnList = "certificate,certificateNO", unique = true),
    })
@org.hibernate.annotations.Table(appliesTo = UserDo.ENTITY_NAME, comment = "用户信息")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDo {
  public static final String ENTITY_NAME = "upms_user";
  private static final String AES_SECRET = AesUtils.getSecret("model.user", "HP_gWER-Y7Gz!E-C");

  @Id
  @Nonnull
  @GeneratedValue(generator = "upms_user_generator")
  @GenericGenerator(name = "upms_user_generator",
      strategy = "cn.idealframework.id.JpaIDGenerator")
  @Column(nullable = false, updatable = false)
  private Long id;

  /** 姓名 */
  @Nonnull
  @Column(nullable = false)
  private String name;

  /** 账号 */
  @Nonnull
  @Column(nullable = false)
  private String account;

  /** 手机号 */
  @Nonnull
  @Column(nullable = false)
  private String mobile;

  /** 邮箱 */
  @Nonnull
  @Column(nullable = false)
  private String email;

  /** 密码 */
  @Nonnull
  @Column(nullable = false, length = 1024)
  private String password;

  /** 性别 */
  @Nonnull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private GenderEnum gender;

  /** 证件类型 */
  @Nonnull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CertificateEnum certificate;

  /** 证件号码 */
  @Nonnull
  @Column(nullable = false)
  private String certificateNO;

  /** 出生日期 */
  @Nullable
  private LocalDate birthday;

  /** 头像 */
  @Nonnull
  @Column(nullable = false)
  private String profilePhoto;

  /** 版本号 */
  @Version
  @Column(nullable = false)
  private long version;

  /** 创建时间 */
  @Nonnull
  @Column(nullable = false)
  private LocalDateTime createdTime;

  /** 更新时间 */
  @Nonnull
  @Column(nullable = false)
  private LocalDateTime updatedTime;

  @Nonnull
  public static UserDo create(@Nonnull CreateUserArgs args) {
    String account = args.getAccount();
    String mobile = args.getMobile();
    Asserts.assertFalse(StringUtils.isBlank(account) && StringUtils.isBlank(mobile),
        "账号与手机号不能同时为空");
    UserDo userDo = new UserDo();
    userDo.setName(args.getName());
    userDo.setAccount(account);
    userDo.setMobile(mobile);
    userDo.setEmail(args.getEmail());
    userDo.setPassword(args.getPassword());
    userDo.setGender(args.getGender());
    userDo.setCertificate(args.getCertificate());
    userDo.setCertificateNO(args.getCertificateNO());
    userDo.setBirthday(args.getBirthday());
    userDo.setProfilePhoto(args.getProfilePhoto());
    userDo.setVersion(0);
    LocalDateTime now = LocalDateTime.now();
    userDo.setCreatedTime(now);
    userDo.setUpdatedTime(now);
    return userDo;
  }

  @Nonnull
  public User toUser() {
    User user = new User();
    user.setId(this.getId());
    user.setName(this.getName());
    user.setAccount(this.getAccount());
    user.setMobile(this.getMobile());
    user.setEmail(this.getEmail());
    user.setGender(this.getGender());
    user.setCertificate(this.getCertificate());
    user.setCertificateNO(this.getCertificateNO());
    user.setBirthday(this.getBirthday());
    user.setProfilePhoto(this.getProfilePhoto());
    user.setCreatedTime(this.getCreatedTime());
    user.setUpdatedTime(this.getUpdatedTime());
    return user;
  }

  @Nonnull
  private String generateUid() {
    return "uuid:" + UUID.randomUUID().toString().replace("-", "");
  }

  @Transient
  private boolean isUid(@Nullable String text) {
    return text != null && text.length() == 37 && text.startsWith("uuid:");
  }

  @Transient
  private boolean isUidCipherText(@Nonnull String ciphertext) {
    String decrypt = decrypt(ciphertext);
    return isUid(decrypt);
  }

  @Nonnull
  public static String encrypt(@Nonnull String text) {
    return AES.encrypt(text, AES_SECRET);
  }

  @Nonnull
  public static String decrypt(@Nonnull String ciphertext) {
    return AES.decrypt(ciphertext, AES_SECRET);
  }

  @Nonnull
  public Long getId() {
    return id;
  }

  public void setId(@Nonnull Long id) {
    this.id = id;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    if (StringUtils.isBlank(name)) {
      name = "";
    }
    this.name = name;
  }

  @Nonnull
  public String getAccount() {
    if (StringUtils.isBlank(account)) {
      return "";
    }
    String decrypt = decrypt(account);
    if (isUid(decrypt)) {
      return "";
    }
    return decrypt;
  }

  public void setAccount(@Nullable String account) {
    if (StringUtils.isBlank(account)) {
      if (isUidCipherText(this.account)) {
        return;
      } else {
        account = generateUid();
      }
    } else {
      CheckUtils.checkMobile(account, "Illegal mobile");
    }
    this.account = encrypt(account);
  }

  @Nonnull
  public String getMobile() {
    if (StringUtils.isBlank(mobile)) {
      return "";
    }
    String decrypt = decrypt(mobile);
    if (isUid(decrypt)) {
      return "";
    }
    return decrypt;
  }

  public void setMobile(@Nullable String mobile) {
    if (StringUtils.isBlank(mobile)) {
      if (isUidCipherText(this.mobile)) {
        return;
      } else {
        mobile = generateUid();
      }
    } else {
      CheckUtils.checkMobile(mobile, "Illegal mobile");
    }
    this.mobile = encrypt(mobile);
  }

  @Nonnull
  public String getEmail() {
    if (StringUtils.isBlank(email)) {
      return "";
    }
    String decrypt = decrypt(email);
    if (isUid(decrypt)) {
      return "";
    }
    return decrypt;
  }

  public void setEmail(@Nullable String email) {
    if (StringUtils.isBlank(email)) {
      if (isUidCipherText(this.email)) {
        return;
      } else {
        email = generateUid();
      }
    } else {
      CheckUtils.checkMobile(email, "Illegal mobile");
    }
    this.email = encrypt(email);
  }

  @Nonnull
  public String getPassword() {
    if (StringUtils.isBlank(password)) {
      return "";
    }
    return decrypt(password);
  }

  public void setPassword(@Nonnull String password) {
    Asserts.notBlank(password, "密码不能为空");
    this.password = encrypt(password);
  }

  @Nonnull
  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(@Nullable GenderEnum gender) {
    if (gender == null) {
      gender = GenderEnum.SECRET;
    }
    this.gender = gender;
  }

  @Nullable
  public CertificateEnum getCertificate() {
    if (certificate == CertificateEnum.NONE) {
      return null;
    }
    return certificate;
  }

  public void setCertificate(@Nullable CertificateEnum certificate) {
    if (certificate == null) {
      certificate = CertificateEnum.NONE;
    }
    this.certificate = certificate;
  }

  @Nonnull
  public String getCertificateNO() {
    return certificateNO;
  }

  public void setCertificateNO(@Nullable String certificateNO) {
    if (StringUtils.isBlank(certificateNO)) {
      certificateNO = "";
    }
    this.certificateNO = certificateNO;
  }

  @Nullable
  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(@Nullable LocalDate birthday) {
    this.birthday = birthday;
  }

  @Nonnull
  public String getProfilePhoto() {
    return profilePhoto;
  }

  public void setProfilePhoto(@Nullable String profilePhoto) {
    if (StringUtils.isBlank(profilePhoto)) {
      profilePhoto = "";
    }
    this.profilePhoto = profilePhoto;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  @Nonnull
  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(@Nonnull LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  @Nonnull
  public LocalDateTime getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(@Nonnull LocalDateTime updatedTime) {
    this.updatedTime = updatedTime;
  }
}
