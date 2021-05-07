/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/18
 */

package org.bnjjong.mybatis;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * create on 2020/12/18. create by IntelliJ IDEA.
 *
 * <pre>
 *  Mybatis Config 설정 통해 Factory 객체를 생성하고 관리하는 역활을 한다.
 *  기본적으로 외부에서는 접근할 수 없다.
 *  pojo 형태의 Mybatis 세션 관리.
 * </pre>
 *
 * @author bnjjong
 * @version 1.0
 * @see SqlSession
 * @see SqlSessionFactory
 * @since 14+
 */
@Slf4j
class MybatisSessionGenerator {

  /**
   * session Factory 객체 생성시 생성 된다.
   */
  private final SqlSessionFactory sessionFactory;


  /**
   * <pre>
   * Mybatis Config 파일로 SqlSessionFactory 생성 한다.
   * </pre>
   *
   * @param path Mybatis config 파일 경로. ex) sqlmap/mybatis-config.xml
   * @throws IllegalArgumentException config path 가 정확하지 않을 경우
   */
  MybatisSessionGenerator(String path) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("register sql session, config path is \"{}\"", path);
      }
      var inputStream = Resources.getResourceAsStream(path);
      this.sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      throw new IllegalArgumentException("path may not valid.>>>>>>>>>" + path, e);
    }
  }

  /**
   * Mybatis Config 파일과 environment 명으로 SqlSessionFactory 생성 한다.
   *
   * @param path Mybatis config 파일 경로. ex) sqlmap/mybatis-config.xml
   * @param env environment 이름
   * @throws IllegalArgumentException config path 가 정확하지 않거나 environment명이 올바르지 않을 경우
   */
  MybatisSessionGenerator(String path, Environments env) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("register sql session, config path is \"{}\", environment name is \"{}\"", path,
            env.toName());
      }

      var inputStream = Resources.getResourceAsStream(path);
      this.sessionFactory = new SqlSessionFactoryBuilder().build(inputStream, env.toName());
    } catch (IOException e) {

      var msg = String.
          format("check path and environment >>>>>>>>> path : {%s}, environment : {%s}",
              path, env);
      throw new IllegalArgumentException(msg, e);
    }
  }

  /**
   *
   * <pre>
   * default로 등록된 {@cdoe sessionFactory}를 가져온다.
   * </pre>
   *
   * @return SqlSessionFactory 리턴.
   */
  SqlSessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
