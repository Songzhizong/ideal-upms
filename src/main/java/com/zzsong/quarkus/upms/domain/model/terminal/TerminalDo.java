package com.zzsong.quarkus.upms.domain.model.terminal;

import com.zzsong.quarkus.upms.common.Asserts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author 宋志宗 on 2021/8/24
 */
@SuppressWarnings("unused")
@Entity
@Table(name = TerminalDo.ENTITY_NAME,
    indexes = {
        @Index(name = "uk_name", columnList = "name", unique = true)
    })
@org.hibernate.annotations.Table(appliesTo = TerminalDo.ENTITY_NAME, comment = "终端信息")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TerminalDo {
  public static final String ENTITY_NAME = "upms_terminal";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 终端名称 */
  @Nonnull
  @Column(nullable = false)
  private String name;

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
  public static TerminalDo create(@Nonnull String name) {
    TerminalDo terminalDo = new TerminalDo();
    terminalDo.setName(name);
    terminalDo.setVersion(0);
    LocalDateTime now = LocalDateTime.now();
    terminalDo.setCreatedTime(now);
    terminalDo.setUpdatedTime(now);
    return terminalDo;
  }

  @Nonnull
  public Terminal toTerminal() {
    Terminal terminal = new Terminal();
    terminal.setId(this.getId());
    terminal.setName(this.getName());
    terminal.setCreatedTime(this.getCreatedTime());
    terminal.setUpdatedTime(this.getUpdatedTime());
    return terminal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  public void setName(@Nonnull String name) {
    Asserts.notBlank(name, "终端名称不能为空");
    this.name = name;
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
