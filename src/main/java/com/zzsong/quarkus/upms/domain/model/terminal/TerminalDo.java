package com.zzsong.quarkus.upms.domain.model.terminal;

import cn.idealframework.util.Asserts;
import com.zzsong.quarkus.upms.domain.model.terminal.args.UpdateTerminalArgs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TerminalDo {
  public static final String ENTITY_NAME = "upms_terminal";

  @Id
  @Nonnull
  @GeneratedValue(generator = "upms_terminal_generator")
  @GenericGenerator(name = "upms_terminal_generator",
      strategy = "cn.idealframework.id.JpaIDGenerator")
  @Column(nullable = false, updatable = false)
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

  public void update(@Nonnull UpdateTerminalArgs args) {
    this.setName(args.getName());
    this.setUpdatedTime(LocalDateTime.now());
  }

  @Nonnull
  public Long getId() {
    return id;
  }

  protected void setId(@Nonnull Long id) {
    this.id = id;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  protected void setName(@Nonnull String name) {
    Asserts.notBlank(name, "终端名称不能为空");
    this.name = name;
  }

  public long getVersion() {
    return version;
  }

  protected void setVersion(long version) {
    this.version = version;
  }

  @Nonnull
  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  protected void setCreatedTime(@Nonnull LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  @Nonnull
  public LocalDateTime getUpdatedTime() {
    return updatedTime;
  }

  protected void setUpdatedTime(@Nonnull LocalDateTime updatedTime) {
    this.updatedTime = updatedTime;
  }
}
