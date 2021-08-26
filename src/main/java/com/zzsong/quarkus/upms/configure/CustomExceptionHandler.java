package com.zzsong.quarkus.upms.configure;

import cn.idealframework.transmission.Result;
import cn.idealframework.transmission.exception.VisibleException;
import cn.idealframework.transmission.exception.VisibleRuntimeException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 自定义异常处理器
 *
 * @author 宋志宗 on 2021/8/25
 */
@Provider
public class CustomExceptionHandler implements ExceptionMapper<Throwable> {
  @Override
  public Response toResponse(Throwable exception) {
    if (exception instanceof IllegalArgumentException) {
      return Response.status(Response.Status.OK)
          .type(MediaType.APPLICATION_JSON)
          .entity(Result.failure(400, exception.getMessage()))
          .build();
    }
    if (exception instanceof VisibleException) {
      VisibleException ex = (VisibleException) exception;
      Result<Void> result = Result.failure(ex.code(), ex.message());
      return Response.status(ex.httpStatus())
          .type(MediaType.APPLICATION_JSON)
          .entity(result)
          .build();
    }
    if (exception instanceof VisibleRuntimeException) {
      VisibleRuntimeException ex = (VisibleRuntimeException) exception;
      Result<Void> result = Result.failure(ex.code(), ex.message());
      return Response.status(ex.httpStatus())
          .type(MediaType.APPLICATION_JSON)
          .entity(result)
          .build();
    }
    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON)
        .entity(Result.exception(exception))
        .build();
  }
}
