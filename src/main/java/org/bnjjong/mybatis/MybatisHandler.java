/*
 * COPYRIGHT (c) ADOP 2020
 * This software is the proprietary of ADOP
 *
 * @author <a href=“mailto:jordan@adop.cc“>jordan</a>
 * @since 2020/09/07
 */

package org.bnjjong.mybatis;

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

//  private static final Map<String, SqlSessionFactory> sessionMap = new HashMap<>();
//  public static final String DEFAULT_CONN_NAME = "default";
//  private static final String DEFAULT_CONFIG_PATH = "sqlmap/mybatis-config.xml";
//  private static final String DEFAULT_PROPERTY_PATH = "sqlmap/mybatis-config.properties";

//  private MybatisHandler() {
//    throw new IllegalStateException("use only static method.");
//  }

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
   */
  SqlSession getSession(Environments env);

  /**
   *
   * @param env
   * @return
   */
  SqlSessionFactory getSessionFactory(Environments env);

  SqlSession getSession(String configName);
  SqlSessionFactory getSessionFactory(String configName);
  SqlSession getSession(String configName, Environments env);
  SqlSessionFactory getSessionFactory(String configName, Environments env);


}