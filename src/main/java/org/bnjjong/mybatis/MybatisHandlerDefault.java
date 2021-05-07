/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/21
 */

package org.bnjjong.mybatis;

import java.io.FileNotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bnjjong.property.PropertyHandler;
import org.jetbrains.annotations.NotNull;

/**
 * create on 2020/12/21. create by IntelliJ IDEA.
 *
 * <p> Mybatis Session을 핸들링 해주는 Class </p>
 * <p> 이 객체는 {@link MybatisFactory} 객체에서만 생성할 수 있다. </p>
 * <pre>
 *   로딩 순서
 *    - properties 파일 -> properties/mybatis.properties
 *    - default mybatis xml 파일 -> sqlmap/mybatis-config.xml
 * </pre>
 *
 * Code Example :
 * <pre>
 *   MybatisHandler handler = MybatisFactory.getInstance();
 *   SqlSession session = handler.getSession();
 *   ...
 * </pre>
 *
 * @author bnjjong
 * @version 1.0
 * @see MybatisFactory#getInstance()
 * @see PropertyHandler
 * @see SqlSessionFactory
 * @see SqlSession
 * @since 1.0
 */
public final class MybatisHandlerDefault extends MybatisHandlerAbstract {




  /**
   * <p> 생성자 클래스 {@code default}로 되어 있으므로 외부에서 생성할 수 없다. </p>
   * <pre>
   *   {@link this#DEFAULT_PROPERTIES_PATH} 경로에서 {@code Mybatis} config path 정보가 있는 프로퍼티를
   *   읽어 온다.
   * </pre>
   *
   * @throws FileNotFoundException {@link this#DEFAULT_PROPERTIES_PATH} 경로에 파일이 없을 경우
   */
  MybatisHandlerDefault() throws FileNotFoundException {
    super();
  }

  @Override
  public SqlSession getSession() {
    return getSessionFactory().openSession();
  }

  @Override
  public SqlSession getSession(boolean autoCommit) {
    return getSessionFactory().openSession(autoCommit);
  }

  @Override
  public SqlSessionFactory getSessionFactory() {
    return getSessionFactory(DEFAULT_SESSION_NAME);
  }

  @Override
  public SqlSession getSession(@NotNull Environments env) {
    return getSessionFactory(env).openSession();
  }

  @Override
  public SqlSession getSession(@NotNull Environments env, boolean autoCommit) {
    return null;
  }

  @Override
  public SqlSessionFactory getSessionFactory(Environments env) {
    return getSessionFactory(DEFAULT_SESSION_NAME, env);
  }

  @Override
  public SqlSession getSession(@NotNull String configName) {
    return getSessionFactory(configName).openSession();
  }

  @Override
  public SqlSession getSession(@NotNull String configName, boolean autoCommit) {
    return null;
  }

  @Override
  public SqlSessionFactory getSessionFactory(@NotNull String configName) {
    return getSessionFactory(configName, EnvironmentsDefault.EMPTY);
  }

  @Override
  public SqlSession getSession(@NotNull String configName, @NotNull Environments env) {
    return getSessionFactory(configName, env).openSession();
  }

  @Override
  public SqlSession getSession(String configName, Environments env, boolean autoCommit) {
    return getSessionFactory(configName).openSession(autoCommit);
  }

  @Override
  public SqlSessionFactory getSessionFactory(@NotNull String configName, @NotNull Environments env) {
    SqlSessionFactory sessionFactory = sessionManager.get(configName);
    if (sessionFactory == null) {
      String path = propertyHandler.get(PROPERTY_SCOPE_KEY).getString(configName + PATH_SUBFIX);
      MybatisSessionGenerator sessionGenerator;
      if(env == EnvironmentsDefault.EMPTY) {
        sessionGenerator = new MybatisSessionGenerator(path);
      } else {
        sessionGenerator = new MybatisSessionGenerator(path, env);
      }
      sessionFactory = sessionGenerator.getSessionFactory();
      sessionManager.add(configName, sessionFactory);
    }
    return sessionFactory;
  }
}
