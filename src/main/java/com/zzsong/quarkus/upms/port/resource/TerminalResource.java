package com.zzsong.quarkus.upms.port.resource;

import cn.idealframework.transmission.Result;
import com.zzsong.quarkus.upms.application.TerminalService;
import com.zzsong.quarkus.upms.application.args.CreateTerminalArgs;
import com.zzsong.quarkus.upms.application.args.UpdateTerminalArgs;
import com.zzsong.quarkus.upms.domain.model.terminal.Terminal;
import com.zzsong.quarkus.upms.domain.model.terminal.TerminalDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 终端管理
 *
 * @author 宋志宗 on 2021/8/24
 */
@Slf4j
@Path("/upms/terminal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TerminalResource {
  private final TerminalService terminalService;

  /**
   * 新增
   *
   * @param args 新增参数
   * @return 终端信息
   * @author 宋志宗 on 2021/8/25
   */
  @POST
  public Result<Terminal> create(CreateTerminalArgs args) {
    TerminalDo terminal = terminalService.create(args);
    return Result.data(terminal.toTerminal());
  }

  /**
   * 修改终端信息
   *
   * @param id   id
   * @param args 修改参数
   * @return 修改后的终端信息
   * @author 宋志宗 on 2021/8/25
   */
  @PUT
  @Path("/{id}")
  public Result<Terminal> update(@PathParam("id") Long id,
                                 UpdateTerminalArgs args) {
    TerminalDo terminal = terminalService.update(id, args);
    return Result.data(terminal.toTerminal());
  }

  /**
   * 获取终端信息
   *
   * @param id id
   * @return 终端信息
   * @author 宋志宗 on 2021/8/25
   */
  @GET
  @Path("/{id}")
  public Result<Terminal> findById(@PathParam("id") Long id) {
    TerminalDo terminal = terminalService.findById(id);
    return Result.data(terminal.toTerminal());
  }

  /**
   * 获取所有终端信息
   *
   * @return 全部终端信息
   * @author 宋志宗 on 2021/8/25
   */
  @GET
  public Result<List<Terminal>> findAll() {
    List<TerminalDo> terminals = terminalService.findAll();
    List<Terminal> collect = terminals.stream()
        .map(TerminalDo::toTerminal).collect(Collectors.toList());
    return Result.data(collect);
  }

  /**
   * 删除终端
   *
   * @param id id
   * @return 执行结果
   * @author 宋志宗 on 2021/8/25
   */
  @DELETE
  @Path("/{id}")
  public Result<Void> delete(@PathParam("id") Long id) {
    terminalService.delete(id);
    return Result.success();
  }
}
