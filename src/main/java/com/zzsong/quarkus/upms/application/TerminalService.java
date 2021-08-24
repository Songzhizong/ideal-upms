package com.zzsong.quarkus.upms.application;

import com.zzsong.quarkus.upms.application.args.CreateTerminalArgs;
import com.zzsong.quarkus.upms.application.args.UpdateTerminalArgs;
import com.zzsong.quarkus.upms.common.Asserts;
import com.zzsong.quarkus.upms.common.transmission.exception.ResourceNotFoundException;
import com.zzsong.quarkus.upms.domain.model.terminal.TerminalDo;
import com.zzsong.quarkus.upms.domain.model.terminal.TerminalRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 终端管理Service
 *
 * @author 宋志宗 on 2021/8/24
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class TerminalService {
  private final TerminalRepository terminalRepository;

  @Nonnull
  @Transactional(rollbackOn = Throwable.class)
  public TerminalDo create(@Nonnull CreateTerminalArgs args) {
    TerminalDo terminalDo = TerminalDo.create(args.getName());
    terminalRepository.persistAndFlush(terminalDo);
    log.info("新增终端: {} - {}", terminalDo.getId(), terminalDo.getName());
    return terminalDo;
  }

  @Transactional(rollbackOn = Throwable.class)
  public void delete(Long id) {
    Asserts.nonnull(id, "id不能为空");
    TerminalDo byId = terminalRepository.findById(id);
    if (byId == null) {
      log.info("终端: {} 不存在", id);
      return;
    }
    terminalRepository.delete(byId);
    log.info("删除终端: {} - {}", id, byId.getName());
  }

  public TerminalDo findById(Long id) {
    Asserts.nonnull(id, "id不能为空");
    TerminalDo byId = terminalRepository.findById(id);
    if (byId == null) {
      log.info("终端: {} 不存在", id);
      throw new ResourceNotFoundException("终端不存在");
    }
    log.info("成功获取终端: {} 信息", id);
    return byId;
  }

  @Transactional(rollbackOn = Throwable.class)
  public TerminalDo update(Long id, UpdateTerminalArgs args) {
    Asserts.nonnull(id, "id不能为空");
    TerminalDo terminalDo = terminalRepository.findById(id);
    if (terminalDo == null) {
      log.info("终端: {} 不存在", id);
      throw new ResourceNotFoundException("终端不存在");
    }
    terminalDo.setName(args.getName());
    terminalRepository.persistAndFlush(terminalDo);
    log.info("修改终端: {} 信息", id);
    return terminalDo;
  }

  public List<TerminalDo> findAll() {
    Sort sort = Sort.descending("id");
    PanacheQuery<TerminalDo> query = terminalRepository.findAll(sort);
    List<TerminalDo> result = query.list();
    log.info("获取终端数据: {}条", result.size());
    return result;
  }
}
