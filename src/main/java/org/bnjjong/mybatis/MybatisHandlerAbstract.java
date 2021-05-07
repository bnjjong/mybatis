/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/05/07
 */

package org.bnjjong.mybatis;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bnjjong.property.PropertyFactory;
import org.bnjjong.property.PropertyHandler;

/**
 * create on 2021/05/07. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Slf4j
public abstract class MybatisHandlerAbstract implements MybatisHandler{
  /**
   * {@code SqlSessionFactory} 오브젝트를 관리해 주는 {@code HashMap} Class
   */
  protected final SessionManager sessionManager;

  /**
   * <p> default session 명. </p>
   * {@link this#sessionManager}의  default key 값.
   */
  protected static final String DEFAULT_SESSION_NAME = "default";

  /**
   * default mybatis config path 정보를 가지고 있는 properties 위치
   */
  protected static final String DEFAULT_PROPERTIES_PATH = "properties/mybatis.properties";

  /**
   * {@link this#DEFAULT_PROPERTIES_PATH} key 값으로 정의된 고정된 SUBFIX
   * <p></p>
   */
  protected static final String PATH_SUBFIX=".config.path";

  /**
   * property scope 값
   * {@code PropertyHandler} 사용시 중복 되는 것을 방지 하기 위해 scope으로 분리하여 사용 한다.
   */
  protected static final String PROPERTY_SCOPE_KEY = "mybatis";

  /**
   * Property를 제어 할 수 있는 프로퍼티 핸들러 객체
   */
  protected final PropertyHandler propertyHandler;

  /**
   * <p> 생성자 클래스 {@code default}로 되어 있으므로 외부에서 생성할 수 없다. </p>
   * <pre>
   *   {@link this#DEFAULT_PROPERTIES_PATH} 경로에서 {@code Mybatis} config path 정보가 있는 프로퍼티를
   *   읽어 온다.
   * </pre>
   *
   * @throws FileNotFoundException {@link this#DEFAULT_PROPERTIES_PATH} 경로에 파일이 없을 경우
   */
  protected MybatisHandlerAbstract() throws FileNotFoundException {
    this.propertyHandler = PropertyFactory.getInstance();
    this.sessionManager = new SessionManager();
    try {
      // 경로에 문제가 있다면 IllegalAragument 에러 발생할 수도 있다
      this.propertyHandler.add(PROPERTY_SCOPE_KEY, DEFAULT_PROPERTIES_PATH);
    } catch (IllegalArgumentException e) {
      String msg = String.format("make sure place your default mybatis property file >>>>>>>> %s", DEFAULT_PROPERTIES_PATH);
      log.error(msg);
      throw new FileNotFoundException(e.getMessage());
    }
  }
}
