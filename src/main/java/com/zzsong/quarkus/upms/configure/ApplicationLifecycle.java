package com.zzsong.quarkus.upms.configure;

import cn.idealframework.id.IDGeneratorFactory;
import com.zzsong.quarkus.upms.common.log.Logger;
import com.zzsong.quarkus.upms.common.log.LoggerFactory;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * @author 宋志宗 on 2021/8/25
 */
@ApplicationScoped
@RequiredArgsConstructor
public class ApplicationLifecycle {
  private static final Logger log = LoggerFactory.getLogger(ApplicationLifecycle.class);
  private final IDGeneratorFactory idGeneratorFactory;

  void onStart(@Observes StartupEvent startupEvent) {

  }

  void onShutdown(@Observes ShutdownEvent shutdownEvent) {
    idGeneratorFactory.release();
  }
}
