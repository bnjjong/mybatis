/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/21
 */

package org.bnjjong.mybatis;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * create on 2019-06-07.
 * <pre>
 *     Mybatis 세션을 관리하는 {@code Interface}.
 * </pre>
 * <pre>
 *   아래 로딩 순서는 구현 클래스에서 직접 구현해야 함.
 *   로딩 순서
 *    - properties 파일 -> properties/mybatis.properties
 *    - 프로퍼티에 정의된 {@code default.config.path} 에 정의된 패스에서 config 파일을 읽어 온다.
 * </pre>
 *
 * Code Example :
 * {@link MybatisFactory} 객체를 사용한다고 하면
 * <pre>
 *   MybatisHandler handler = MybatisFactory.getInstance();
 *   SqlSession session = handler.getSession();
 *   ...
 * </pre>
 * @author bnjjong
 * @version 1.0 (mybatis version : 3.5.5)
 * @see SqlSession
 * @see SqlSessionFactory
 * @see MybatisFactory#getInstance()
 * @since 1.0
 */
public interface MybatisHandler {

  /**
   * <pre>
   * default로 설정하여 생성된 세션 객체을 리턴 한다.
   * </pre>
   *
   * @return {@code SqlSession} 객체를 반환.
   * @see MybatisHandlerAbstract#DEFAULT_PROPERTIES_PATH
   * @see SqlSession
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  SqlSession getSession();

  /**
   * <pre>
   * default로 설정된 세션 객체을 리턴 한다.
   * </pre>
   *
   * @param autoCommit {@code true} auto commit 된다.
   * @return {@code SqlSession} 객체를 반환.
   * @see SqlSession
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  SqlSession getSession(boolean autoCommit);

  /**
   * <pre>
   * default로 설정된 세션 팩토리 객체를 리턴 한다.
   * </pre>
   *
   * @return {@code SqlSessionFactory} 객체를 반환.
   * @see SqlSessionFactory
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  SqlSessionFactory getSessionFactory();

  /**
   * <pre>
   * default로 설정된 설정파일의 다른 환경 설정의 세션 객체를 리턴 한다.
   * </pre>
   *
   * @param env 환경 설정 정보
   * @return {@code SqlSession} 객체를 반환.
   * @see SqlSession
   * @throws  IllegalStateException 환경 설정 정보가 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(Environments env);

  /**
   * <pre>
   * default로 설정된 설정파일의 다른 환경 설정의 세션 객체를 리턴 한다.
   * </pre>
   *
   * @param env 환경 설정 정보
   * @param autoCommit {@code true} auto commit 된다.
   * @return {@code SqlSession} 객체를 반환.
   * @see SqlSession
   * @throws  IllegalStateException 환경 설정 정보가 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(Environments env, boolean autoCommit);

  /**
   *
   * @param env 환경 설정 정보
   * @return {@code SqlSessionFactory} 객체를 반환.
   * @see SqlSessionFactory
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(Environments env);

  /**
   * <pre>
   *   config path로 등록된 session 을 반환 한다.
   * </pre>
   *
   * @param configName 등록한 config명
   * @return {@code SqlSession} 객체를 반환.
   * @see SqlSession
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   * @throws ConfigNotFoundException configName 정보를 찾을 수 없을때 발생 한다.
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(String configName);

  /**
   * <pre>
   *   config path로 등록된 session 을 반환 한다.
   * </pre>
   * @param configName 등록한 config명
   * @param autoCommit {@code true} auto commit 된다.
   * @return {@code SqlSession} 객체를 반환
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   * @throws ConfigNotFoundException configName 정보를 찾을 수 없을때 발생 한다.
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(String configName, boolean autoCommit);

  /**
   * <pre>
   *   config path로 등록된 session 을 반환 한다.
   * </pre>
   * @param configName 등록한 config명
   * @return {@code SqlSessionFactory} 객체를 반환.
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   * @throws ConfigNotFoundException configName 정보를 찾을 수 없을때 발생 한다.
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(String configName);

  /**
   *
   * @param configName 등록한 config명
   * @param env 환경 설정 정보
   * @return {@code SqlSession} 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   * @throws ConfigNotFoundException configName 정보를 찾을 수 없을때 발생 한다.
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(String configName, Environments env);

  SqlSession getSession(String configName, Environments env, boolean autoCommit);



  /**
   *
   * @param configName 등록한 config명
   * @param env
   * @return {@code SqlSessionFactory} 객체를 반환.
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(String configName, Environments env);


}