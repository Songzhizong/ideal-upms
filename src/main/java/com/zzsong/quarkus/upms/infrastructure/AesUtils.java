package com.zzsong.quarkus.upms.infrastructure;

import cn.idealframework.lang.StringUtils;
import cn.idealframework.log.Logger;
import cn.idealframework.log.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 宋志宗 on 2021/8/26
 */
public final class AesUtils {
  private static final Logger log = LoggerFactory.getLogger(AesUtils.class);
  private static final Properties PROPERTIES;

  static {
    InputStream inputStream = Thread.currentThread()
        .getContextClassLoader().getResourceAsStream("aes_secret.properties");
    if (inputStream == null) {
      log.error("无法获取aes_secret.json文件");
      throw new AssertionError("缺失配置文件aes_secret.properties");
    }
    try (InputStream is = inputStream) {
      Properties properties = new Properties();
      properties.load(is);
      PROPERTIES = properties;
    } catch (IOException e) {
      throw new RuntimeException("读取配置文件aes_secret.properties异常", e);
    }
  }

  @Nonnull
  public static String getSecret(@Nonnull String key, @Nonnull String defaultValue) {
    String property = PROPERTIES.getProperty(key);
    if (StringUtils.isNotBlank(property)) {
      return property;
    }
    log.warn("{} 未配置, 使用默认值", key);
    return defaultValue;
  }
}
