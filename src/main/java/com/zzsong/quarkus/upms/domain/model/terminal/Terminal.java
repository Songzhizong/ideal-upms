package com.zzsong.quarkus.upms.domain.model.terminal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author 宋志宗 on 2021/8/24
 */
@Getter
@Setter
public class Terminal {
  private long id;

  private String name;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;
}
