/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/18
 */

package org.bnjjong.mybatis;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * create on 2020/12/18. create by IntelliJ IDEA.
 *
 * <pre>
 *   Mybatis Config 설정 통해 Factory 객체를 생성하는 역활을 한다.
 *   기본적으로 외부에서는 접근할 수 없다.
 * </pre>
 *
 *
 * @author bnjjong
 * @see SqlSessionFactory
 * @since 14+
 */
@Deprecated
@Slf4j
public final class MybatisRegister {
  MybatisRegister() {}

  /**
   * Mybatis Config 파일로 SqlSessionFactory 생성 후 리턴.
   * @param path Mybatis config 파일 경로. ex) sqlmap/mybatis-config.xml
   * @return 설정파일에 의해 등록된 SqlSessionFactory 객체.
   * @throws IllegalArgumentException config path 가 정확하지 않을 경우
   */
  SqlSessionFactory regSqlSession(String path) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("register sql session, config path is \"{}\"", path);
      }
      InputStream inputStream = Resources.getResourceAsStream(path);
      return new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      throw new IllegalArgumentException("path may not valid.>>>>>>>>>" + path, e);
    }
  }


  /**
   * Mybatis Config 파일로 SqlSessionFactory 생성 후 리턴.
   * @param path Mybatis config 파일 경로. ex) sqlmap/mybatis-config.xml
   * @param env environment 이름
   * @return 설정파일에 의해 등록된 SqlSessionFactory 객체.
   * @throws IllegalArgumentException config path 가 정확하지 않거나 environment명이 올바르지 않을 경우
   */
  SqlSessionFactory regSqlSession(String path, String env) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("register sql session, config path is \"{}\", environment name is \"{}\"", path, env);
      }

      InputStream inputStream = Resources.getResourceAsStream(path);
      return new SqlSessionFactoryBuilder().build(inputStream, env);
    } catch (IOException e) {

      throw new IllegalArgumentException(
          String.format("check path and environment >>>>>>>>> path : {%s}, environment : {%s}", path, env),
          e);
    }
  }


}
