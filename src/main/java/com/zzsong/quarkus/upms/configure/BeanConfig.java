package com.zzsong.quarkus.upms.configure;

import cn.idealframework.id.DatabaseIDGeneratorFactory;
import cn.idealframework.id.IDGenerator;
import cn.idealframework.id.IDGeneratorFactory;
import cn.idealframework.id.JpaIDGenerator;
import cn.idealframework.log.Logger;
import cn.idealframework.log.LoggerFactory;
import cn.idealframework.trace.TraceContextHolder;
import cn.idealframework.trace.impl.IDGeneratorTraceIdGenerator;
import com.zzsong.quarkus.upms.infrastructure.password.MD5PasswordEncoder;
import com.zzsong.quarkus.upms.infrastructure.password.PasswordEncoder;
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
    IDGeneratorFactory factory = new DatabaseIDGeneratorFactory(0, applicationName, dataSource);
//    IDGeneratorFactory factory = new FixedIDGeneratorFactory(0, 0);
    IDGenerator jpaIdGenerator = factory.getGenerator("JPA");
    JpaIDGenerator.setIdGenerator(jpaIdGenerator);
    IDGenerator traceIdGenerator = factory.getGenerator("TRACE");
    TraceContextHolder.setTraceIdGenerator(new IDGeneratorTraceIdGenerator(traceIdGenerator));
    return factory;
  }

  @Produces
  public PasswordEncoder passwordEncoder() {
    return new MD5PasswordEncoder("Vd3.HARj_itxmX*Z7WTx!njtywesJxP8");
  }

}
