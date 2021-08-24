package com.zzsong.quarkus.upms.domain.model.terminal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author 宋志宗 on 2021/8/24
 */
@ApplicationScoped
public class TerminalRepository implements PanacheRepository<TerminalDo> {

  @Nonnull
  public Optional<TerminalDo> findByName(@Nonnull String name) {
    return find("name", name).firstResultOptional();
  }
}
