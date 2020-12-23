/*
 * COPYRIGHT (c) ADOP 2020
 * This software is the proprietary of ADOP
 *
 * @author <a href=“mailto:jordan@adop.cc“>jordan</a>
 * @since 2020/09/07
 */

package org.bnjjong.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.validation.Schema;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * create on 2019-06-07.
 * <p> mybatis session factory class</p>
 * <pre>
 *     resources/sqlmap/mybatis-config.xml 설정 파일을 default로 바라보게 되어 있음.
 *     여러 세션을 사용해야 한다면 아래처럼 세션을 등록 할 수 있다.
 *     ex)
 *     MybatisFactory.regSqlSession("sessionName", "sqlmap/my-config.xml");
 *     SqlSessionFactory sf = MybatisFactory.getSessionFactory("sessionName");
 * </pre>
 *
 * @author jshan
 * @version 1.0 (mybatis version : 3.5.5)
 * @since 1.8
 */
@Slf4j
public final class MybatisFactory {

  private static final Map<String, SqlSessionFactory> sessionMap = new HashMap<>();
  public static final String DEFAULT_CONN_NAME = "default";
  private static final String DEFAULT_CONFIG_PATH = "sqlmap/mybatis-config.xml";

  private MybatisFactory() {
    throw new IllegalStateException("use only static method.");
  }

  /**
   * default로 등록한 세션을 리턴 함. sqlmap/mybatis-config.xml 에 설정 정보를 리턴. default는 {@code auto commit}
   * 상태이다..
   *
   * @return 디폴트 세션을 반환
   * @throws MybatisFactoryException 디폴트 설정을 안하고 호출한 경우
   */
  public static SqlSession getSession() {
    return getSessionFactory().openSession(true);
  }

  public static SqlSession getSession(boolean autoCommit) {
    return getSessionFactory().openSession(autoCommit);
  }

  public static SqlSession getSession(String name) {
    return getSession(name, true);
  }
  /**
   * 등록된 세션을 반환한다.
   *
   * @param name 세션이름
   * @return 등록된 세션을 반환
   * @throws MybatisFactoryException 해당 이름으로된 세션이 없을 경우
   */
  public static SqlSession getSession(String name, boolean autoCommit) {
    if (sessionMap.get(name) == null) {
      throw new MybatisFactoryException("there is no " + name + " session ");
    }
    return sessionMap.get(name).openSession(autoCommit);
  }

  /**
   * 커스텀 세션을 리턴 함. sqlmap/mybatis-스키마-config.xml 에 설정 정보를 리턴.
   *
   * @return 커스텀 세션을 반환
   * @throws MybatisFactoryException 커스텀 설정을 안하고 호출한 경우
   */
  public static SqlSession getSession(Schema schema) {
    return getSession(schema, true);
  }

  public static SqlSession getSession(Schema schema, boolean autoCommit) {
    return getSessionFactory(schema).openSession(autoCommit);
  }

  /**
   * default로 등록한 세션 팩토리를 리턴 함. sqlmap/mybatis-config.xml 에 설정 정보를 리턴
   *
   * @return 디폴트 세션을 반환
   * @throws MybatisFactoryException 디폴트 설정을 안하고 호출한 경우
   */
  public static SqlSessionFactory getSessionFactory() {
    if (sessionMap.get(DEFAULT_CONN_NAME) == null) {
      regSqlSession(DEFAULT_CONN_NAME, DEFAULT_CONFIG_PATH);
      if (sessionMap.get(DEFAULT_CONN_NAME) == null) {
        throw new MybatisFactoryException(
            "there is no default session! if you want use default session, "
                + "make sure that the config file is placed `sqlmap/mybatis-config.xml` ");
      }
    }
    return sessionMap.get(DEFAULT_CONN_NAME);
  }

  /**
   * 커스텀 세션 팩토리를 리턴 함. sqlmap/mybatis-스키마-config.xml 에 설정 정보를 리턴
   *
   * @return 커스텀 세션을 반환
   * @throws MybatisFactoryException 디폴트 설정을 안하고 호출한 경우
   */
  public static SqlSessionFactory getSessionFactory(Schema schema) {
    if (sessionMap.get(String.valueOf(schema)) == null) {
      String configPath = String.format("sqlmap/mybatis-%s-config.xml", schema);
      regSqlSession(String.valueOf(schema), configPath);
      if (sessionMap.get(String.valueOf(schema)) == null) {
        throw new MybatisFactoryException(String.format(
            "there is no custom session! if you want use custom session, "
                + "make sure that the config file is placed `sqlmap/mybatis-%s-config.xml` ",
            schema));

      }
    }
    return sessionMap.get(String.valueOf(schema));
  }

  /**
   * 세션을 등록함.
   *
   * @param name 해당 세션 네임
   * @param path config 경로 (ex) sqlmap/custom-config.xml);
   */
  public static void regSqlSession(String name, String path) {
    try {
      InputStream inputStream = Resources.getResourceAsStream(path);
      SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      sessionMap.put(name, sessionFactory);
    } catch (IOException e) {
      throw new IllegalArgumentException("path is not collect!", e);
    }
  }



  /**
   * SQL Enviroment 세션을 등록함.
   *
   * @param name 해당 세션 네임
   * @param env  SQL Enviroment
   */
  public static void regSqlEnvSession(String name, String env) throws IOException {
    try {
      InputStream inputStream = Resources.getResourceAsStream(DEFAULT_CONFIG_PATH);
      SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream, env);
      sessionMap.put(name, sessionFactory);
    } catch (IOException e) {
      throw new IllegalArgumentException("path is not collect!", e);
    }
  }
}