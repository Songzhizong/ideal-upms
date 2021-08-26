package com.zzsong.quarkus.upms.configure;

import cn.idealframework.lang.StringUtils;
import cn.idealframework.log.Logger;
import cn.idealframework.log.LoggerFactory;
import cn.idealframework.trace.TraceConstants;
import cn.idealframework.trace.TraceContextHolder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Nonnull;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 * @author 宋志宗 on 2021/8/25
 */
@Provider
@Priority(Priorities.USER)
public class TraceFilter implements ContainerRequestFilter, ContainerResponseFilter {
  private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);
  private static final String REQUEST_TIME_MILLIS = "requestTimeMillis";

  @ConfigProperty(name = "quarkus.application.name", defaultValue = "ideal-upms")
  protected String application;

  @Override
  public void filter(@Nonnull ContainerRequestContext requestContext) {
    requestContext.setProperty(REQUEST_TIME_MILLIS, System.currentTimeMillis());
    UriInfo uriInfo = requestContext.getUriInfo();
    String path = uriInfo.getPath();
    String traceId = requestContext.getHeaderString(TraceConstants.HTTP_HEADER_TRACE_ID);
    if (StringUtils.isBlank(traceId)) {
      traceId = null;
    }
    String spanId = requestContext.getHeaderString(TraceConstants.HTTP_HEADER_SPAN_ID);
    if (StringUtils.isBlank(traceId)) {
      spanId = null;
    }
    TraceContextHolder.init("http", traceId, spanId);
    log.info("\nRequest path: {}", path);

  }

  @Override
  public void filter(@Nonnull ContainerRequestContext requestContext,
                     @Nonnull ContainerResponseContext responseContext) {
    Object requestTimeMillisProperty = requestContext.getProperty(REQUEST_TIME_MILLIS);
    if (requestTimeMillisProperty != null) {
      long requestTimeMillis = (long) requestTimeMillisProperty;
      UriInfo uriInfo = requestContext.getUriInfo();
      String path = uriInfo.getPath();
      long consuming = System.currentTimeMillis() - requestTimeMillis;
      log.info("Request URI: {} 执行耗时: {}ms", path, consuming);
    }
    TraceContextHolder.release();
  }
}
