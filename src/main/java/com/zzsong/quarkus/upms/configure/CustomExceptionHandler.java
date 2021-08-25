package com.zzsong.quarkus.upms.configure;

import cn.idealframework.transmission.Result;

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
    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON)
        .entity(Result.exception(exception))
        .build();
  }
}
