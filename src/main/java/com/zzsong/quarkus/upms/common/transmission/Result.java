package com.zzsong.quarkus.upms.common.transmission;

import cn.idealframework.core.transmission.CommonResMsg;
import cn.idealframework.core.transmission.ResMsg;
import cn.idealframework.core.transmission.exception.VisibleException;
import cn.idealframework.core.transmission.exception.VisibleRuntimeException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * @author 宋志宗 on 2021/8/24
 */
@Getter
@Setter
public class Result<T> {
  @Nullable
  private String traceId;

  private boolean success = true;

  private int code = 200;

  @Nullable
  private String message;

  @Nullable
  private T data;

  public Result() {
  }

  @Nonnull
  public static <T> Result<T> create() {
    Result<T> res = new Result<>();
    res.setTraceId("");
    return res;
  }

  @Nonnull
  public static <T> Result<T> success() {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(CommonResMsg.SUCCESS.code());
    res.setMessage(CommonResMsg.SUCCESS.message());
    return res;
  }

  @Nonnull
  public static <T> Result<T> success(@Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(CommonResMsg.SUCCESS.code());
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> success(int code, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(code);
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> failure(int code, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(code);
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> failure(@Nonnull ResMsg resMsg) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(resMsg.code());
    res.setMessage(resMsg.message());
    return res;
  }

  @Nonnull
  public static <T> Result<T> success(@Nonnull ResMsg resMsg) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(resMsg.code());
    res.setMessage(resMsg.message());
    return res;
  }

  @Nonnull
  public static <T> Result<T> success(@Nonnull ResMsg resMsg, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(resMsg.code());
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> data(@Nullable T data) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(CommonResMsg.SUCCESS.code());
    res.setMessage(CommonResMsg.SUCCESS.message());
    res.setData(data);
    return res;
  }

  @Nonnull
  public static <T> Result<T> data(@Nonnull T data, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(true);
    res.setCode(CommonResMsg.SUCCESS.code());
    res.setMessage(message);
    res.setData(data);
    return res;
  }

  @Nonnull
  public static <T> Result<T> err() {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(CommonResMsg.BAD_REQUEST.code());
    res.setMessage(CommonResMsg.BAD_REQUEST.message());
    return res;
  }

  @Nonnull
  public static <T> Result<T> err(@Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(CommonResMsg.BAD_REQUEST.code());
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> err(@Nonnull ResMsg resMsg) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(resMsg.code());
    res.setMessage(resMsg.message());
    return res;
  }

  @Nonnull
  public static <T> Result<T> err(@Nonnull ResMsg resMsg, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(resMsg.code());
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> message(boolean success, int code, @Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(code);
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public static <T> Result<T> exception(@Nonnull Throwable t) {
    if (t instanceof VisibleException) {
      return failure((VisibleException) t);
    }
    if (t instanceof VisibleRuntimeException) {
      return failure((VisibleRuntimeException) t);
    }
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(CommonResMsg.INTERNAL_SERVER_ERROR.code());
    res.setMessage(t.getMessage());
    return res;
  }

  @Nonnull
  public static <T> Result<T> exception(@Nonnull String message) {
    Result<T> res = new Result<>();
    res.setSuccess(false);
    res.setCode(CommonResMsg.INTERNAL_SERVER_ERROR.code());
    res.setMessage(message);
    return res;
  }

  @Nonnull
  public <R> Result<R> convert(@Nullable Function<T, R> function) {
    Result<R> retRes = new Result<>();
    retRes.setTraceId(this.getTraceId());
    retRes.setSuccess(this.isSuccess());
    retRes.setCode(this.getCode());
    retRes.setMessage(this.getMessage());
    if (this.getData() != null && function != null) {
      retRes.setData(function.apply(this.getData()));
    }
    return retRes;
  }
}
