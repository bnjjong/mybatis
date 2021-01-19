/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/21
 */

package org.bnjjong.mybatis;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bnjjong.property.PropertyFactory;
import org.bnjjong.property.PropertyHandler;

/**
 * create on 2020/12/21. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 * <pre>
 *   로딩 순서
 *    - properties 파일 -> properties/mybatis.properties
 *    - default mybatis xml 파일 -> sqlmap/mybatis-config.xml
 * </pre>
 *
 * @author bnjjong
 * @version 1.0
 * @since 14+
 */
public final class MybatisHandlerDefault implements MybatisHandler {
  private static final Map<String, SqlSessionFactory> sessionMap = new HashMap<>();
  private static final String DEFAULT_SESSION_NAME = "default";
//  static final String DEFAULT_CONFIG_PATH = "sqlmap/mybatis-config.xml";
  public static final String PROPERTY_SCOPE_KEY = "mybatis";
  private static final String DEFAULT_PROPERTIES_PATH = "properties/mybatis.properties";
  private static final String PATH_SUBFIX=".config.path";

  private final PropertyHandler handler;

  /**
   *
   * @throws FileNotFoundException default properties 파일이 없을 때
   */
  MybatisHandlerDefault() throws FileNotFoundException {
    this.handler = PropertyFactory.getInstance();
    // 경로에 문제가 있다면 IllegalAragument 에러 발생할 수도 있다
    try {
      this.handler.add(PROPERTY_SCOPE_KEY, DEFAULT_PROPERTIES_PATH);
    } catch (IllegalArgumentException e) {
      String msg = String.format("make sure place your default mybatis property file>>>>>>>> %s", DEFAULT_PROPERTIES_PATH);
      throw new FileNotFoundException(msg);
    }
  }

  @Override
  public SqlSession getSession() {
    return getSessionFactory().openSession();
  }

  @Override
  public SqlSessionFactory getSessionFactory() {
    return getSessionFactory(DEFAULT_SESSION_NAME);
  }

  @Override
  public SqlSession getSession(Environments env) {
    return getSessionFactory(env).openSession();
  }

  @Override
  public SqlSessionFactory getSessionFactory(Environments env) {
    return getSessionFactory(DEFAULT_SESSION_NAME, env);
  }

  @Override
  public SqlSession getSession(String configName) {
    return getSessionFactory(configName).openSession();
  }

  @Override
  public SqlSessionFactory getSessionFactory(String configName) {
    SqlSessionFactory sessionFactory = sessionMap.get(configName);
    if (sessionFactory == null) {
      String path = handler.get(PROPERTY_SCOPE_KEY).getString(configName + PATH_SUBFIX);
      MybatisSessionGenerator sessionGenerator = new MybatisSessionGenerator(path);
      sessionFactory = sessionGenerator.getSessionFactory();
      sessionMap.put(configName, sessionFactory);
    }
    return sessionFactory;
  }

  @Override
  public SqlSession getSession(String configName, Environments env) {
    return getSessionFactory(configName, env).openSession();
  }

  @Override
  public SqlSessionFactory getSessionFactory(String configName, Environments env) {
    SqlSessionFactory sessionFactory = sessionMap.get(configName);
    if (sessionFactory == null) {
      String path = handler.get(PROPERTY_SCOPE_KEY).getString(configName + PATH_SUBFIX);
      MybatisSessionGenerator sessionGenerator = new MybatisSessionGenerator(path, env);
      sessionFactory = sessionGenerator.getSessionFactory();
      sessionMap.put(configName, sessionFactory);
    }
    return sessionFactory;
  }
}
