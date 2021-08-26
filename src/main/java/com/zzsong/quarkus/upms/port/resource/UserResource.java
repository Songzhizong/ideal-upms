package com.zzsong.quarkus.upms.port.resource;

import cn.idealframework.transmission.Result;
import com.zzsong.quarkus.upms.application.UserService;
import com.zzsong.quarkus.upms.domain.model.user.User;
import com.zzsong.quarkus.upms.domain.model.user.UserDo;
import com.zzsong.quarkus.upms.domain.model.user.args.CreateUserArgs;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
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
}
