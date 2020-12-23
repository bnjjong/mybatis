/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/21
 */

package org.bnjjong.mybatis;

import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * create on 2019-06-07.
 * <pre>
 *     Mybatis 세션을 제어기능에 대한 인터페이스.
 * </pre>
 *
 * @author bnjjong
 * @version 1.0 (mybatis version : 3.5.5)
 * @see SqlSession
 * @see SqlSessionFactory
 * @since 14+
 */
public interface MybatisHandler {

  /**
   * default로 설정된 세션 객체을 리턴 한다.
   * @return SqlSession 객체를 반환
   * @throws IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  SqlSession getSession();

  /**
   * default로 설정된 세션 팩토리 객체를 리턴 한다.
   * @return SqlSessionFactory 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  SqlSessionFactory getSessionFactory();

  /**
   * default로 설정된 설정파일의 다른 환경 설정의 세션 객체를 리턴 한다.
   * @param env 환경 설정 정보
   * @return SqlSession 객체를 반환
   * @throws  IllegalStateException 환경 설정 정보가 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(Environments env);

  /**
   *
   * @param env 환경 설정 정보
   * @return SqlSessionFactory 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(Environments env);

  /**
   *
   * @param configName
   * @return SqlSession 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(String configName);

  /**
   *
   * @param configName
   * @return SqlSessionFactory 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(String configName);

  /**
   *
   * @param configName
   * @param env
   * @return SqlSession 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSession getSession(String configName, Environments env);

  /**
   *
   * @param configName
   * @param env
   * @return SqlSessionFactory 객체를 반환
   * @throws  IllegalStateException 설정 파일 또는 path 등이 올바르지 않을 경우
   */
  @ParametersAreNonnullByDefault
  SqlSessionFactory getSessionFactory(String configName, Environments env);


}