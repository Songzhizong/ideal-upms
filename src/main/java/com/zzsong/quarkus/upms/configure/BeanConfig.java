package com.zzsong.quarkus.upms.configure;

import cn.idealframework.id.DatabaseIDGeneratorFactory;
import cn.idealframework.id.IDGenerator;
import cn.idealframework.id.IDGeneratorFactory;
import cn.idealframework.id.JpaIDGenerator;
import com.zzsong.quarkus.upms.common.log.Logger;
import com.zzsong.quarkus.upms.common.log.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

/**
 * @author 宋志宗 on 2021/8/25
 */
@Dependent
@RequiredArgsConstructor
public class BeanConfig {
  private static final Logger log = LoggerFactory.getLogger(BeanConfig.class);
  private final DataSource dataSource;

  @ConfigProperty(name = "quarkus.application.name", defaultValue = "ideal-upms")
  protected String applicationName;

  @Produces
  public IDGeneratorFactory idGeneratorFactory() {
    log.info("init IDGeneratorFactory");
    DatabaseIDGeneratorFactory factory = new DatabaseIDGeneratorFactory(0, applicationName, dataSource);
    IDGenerator jpaIdGenerator = factory.getGenerator("JPA");
    JpaIDGenerator.setIdGenerator(jpaIdGenerator);
    return factory;
  }
}
