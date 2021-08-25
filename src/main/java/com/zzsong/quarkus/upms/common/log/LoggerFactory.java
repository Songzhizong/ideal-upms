package com.zzsong.quarkus.upms.common.log;

import javax.annotation.Nonnull;

/**
 * @author 宋志宗 on 2021/8/25
 */
public class LoggerFactory {

  @Nonnull
  public static Logger getLogger(@Nonnull Class<?> clazz) {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
    return new LoggerImpl(logger);
  }
}
