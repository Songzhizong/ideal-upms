package com.zzsong.quarkus.upms.configure;

import cn.idealframework.trace.TraceContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

/**
 * @author 宋志宗 on 2021/8/25
 */
@Slf4j
@Provider
@PreMatching
public class TraceFilter implements ContainerRequestFilter, ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext) {
    TraceContextHolder.init("http", null, null);
  }

  @Override
  public void filter(ContainerRequestContext requestContext,
                     ContainerResponseContext responseContext) {
    TraceContextHolder.release();
  }
}
