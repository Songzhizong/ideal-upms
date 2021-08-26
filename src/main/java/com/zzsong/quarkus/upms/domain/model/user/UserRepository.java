package com.zzsong.quarkus.upms.domain.model.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author 宋志宗 on 2021/8/26
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<UserDo> {

}
