package com.zzsong.quarkus.upms.common.log;

import cn.idealframework.trace.TraceContext;
import cn.idealframework.trace.TraceContextHolder;

import java.util.Optional;

/**
 * @author 宋志宗 on 2021/8/25
 */
public class LoggerImpl implements Logger {
  private final org.slf4j.Logger log;

  public LoggerImpl(org.slf4j.Logger log) {
    this.log = log;
  }

  @Override
  public String getName() {
    return log.getName();
  }

  @Override
  public boolean isTraceEnabled() {
    return log.isTraceEnabled();
  }

  @Override
  public void trace(String msg) {
    msg = reformat(msg);
    log.trace(msg);
  }

  @Override
  public void trace(String format, Object arg) {
    format = reformat(format);
    log.trace(format, arg);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    format = reformat(format);
    log.trace(format, arg1, arg2);
  }

  @Override
  public void trace(String format, Object... arguments) {
    format = reformat(format);
    log.trace(format, arguments);
  }

  @Override
  public void trace(String msg, Throwable t) {
    msg = reformat(msg);
    log.trace(msg, t);
  }

  @Override
  public boolean isDebugEnabled() {
    return log.isDebugEnabled();
  }

  @Override
  public void debug(String msg) {
    msg = reformat(msg);
    log.debug(msg);
  }

  @Override
  public void debug(String format, Object arg) {
    format = reformat(format);
    log.debug(format, arg);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    format = reformat(format);
    log.debug(format, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... arguments) {
    format = reformat(format);
    log.debug(format, arguments);
  }

  @Override
  public void debug(String msg, Throwable t) {
    msg = reformat(msg);
    log.debug(msg, t);
  }

  @Override
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  @Override
  public void info(String msg) {
    msg = reformat(msg);
    log.info(msg);
  }

  @Override
  public void info(String format, Object arg) {
    format = reformat(format);
    log.info(format, arg);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    format = reformat(format);
    log.info(format, arg1, arg2);
  }

  @Override
  public void info(String format, Object... arguments) {
    format = reformat(format);
    log.info(format, arguments);
  }

  @Override
  public void info(String msg, Throwable t) {
    msg = reformat(msg);
    log.info(msg, t);
  }

  @Override
  public boolean isWarnEnabled() {
    return log.isWarnEnabled();
  }

  @Override
  public void warn(String msg) {
    msg = reformat(msg);
    log.warn(msg);
  }

  @Override
  public void warn(String format, Object arg) {
    format = reformat(format);
    log.warn(format, arg);
  }

  @Override
  public void warn(String format, Object... arguments) {
    format = reformat(format);
    log.warn(format, arguments);
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    format = reformat(format);
    log.warn(format, arg1, arg2);
  }

  @Override
  public void warn(String msg, Throwable t) {
    msg = reformat(msg);
    log.warn(msg, t);
  }

  @Override
  public boolean isErrorEnabled() {
    return log.isErrorEnabled();
  }

  @Override
  public void error(String msg) {
    msg = reformat(msg);
    log.error(msg);
  }

  @Override
  public void error(String format, Object arg) {
    format = reformat(format);
    log.error(format, arg);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    format = reformat(format);
    log.error(format, arg1, arg2);
  }

  @Override
  public void error(String format, Object... arguments) {
    format = reformat(format);
    log.error(format, arguments);
  }

  @Override
  public void error(String msg, Throwable t) {
    msg = reformat(msg);
    log.error(msg, t);
  }

  private String reformat(String format) {
    Optional<TraceContext> optional = TraceContextHolder.current();
    if (optional.isPresent()) {
      TraceContext traceContext = optional.get();
      String mode = traceContext.getMode();
      String traceId = traceContext.getTraceId();
      String spanId = traceContext.getSpanId();
      format = mode + ":" + traceId + ":" + spanId + " -> " + format;
    }
    return format;
  }
}
