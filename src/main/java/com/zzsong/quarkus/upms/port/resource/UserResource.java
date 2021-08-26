package com.zzsong.quarkus.upms.port.resource;

import cn.idealframework.transmission.Result;
import com.zzsong.quarkus.upms.application.UserService;
import com.zzsong.quarkus.upms.domain.model.user.User;
import com.zzsong.quarkus.upms.domain.model.user.UserDo;
import com.zzsong.quarkus.upms.domain.model.user.args.CreateUserArgs;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 用户管理
 *
 * @author 宋志宗 on 2021/8/26
 */
@Path("/upms/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class UserResource {
  private final UserService userService;

  @POST
  @Path("/register")
  public Result<User> register(CreateUserArgs args) {
    UserDo userDo = userService.register(args);
    return Result.data(userDo.toUser());
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Path("/findByCredentialsAndPassword")
  public Result<User> findByCredentialsAndPassword(@FormParam("credentials") String credentials,
                                                   @FormParam("password") String password) {
    UserDo userDo = userService.authenticate(credentials, password);
    return Result.data(userDo.toUser());
  }
}
