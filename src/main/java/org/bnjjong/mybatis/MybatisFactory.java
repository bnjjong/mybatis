/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/21
 */

package org.bnjjong.mybatis;

import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * create on 2019-06-07.
 * <p> mybatis session factory class</p>
 * <pre>
 *  MybatisHandler 인스턴스 팩토리 클래스.
 *  생성된 인스턴스는 {@code Singleton}으로 관리 된다.
 * </pre>
 *
 * @author bnjjong
 * @version 1.0 (mybatis version : 3.5.5)
 */
@Slf4j
public final class MybatisFactory {

  private MybatisFactory() {
    throw new IllegalStateException("use only static method.");
  }

  /**
   * MybatisHanlder 싱글톤 클래스.
   */
  private static class Singleton {
    private static MybatisHandler INSTANCE;
  }

  /**
   * <pre>
   * propertyHandler 싱글톤 객체를 리턴 한다.
   * </pre>
   *
   * @return singleton PropertyHandler 오브젝트
   * @throws IllegalStateException 리턴할 인스턴스가 {@code null}일 경우 에러가 발생 할 수 있다.
   */
  public static MybatisHandler getInstance() {
    try {
      if (Singleton.INSTANCE == null) {
        Singleton.INSTANCE = new MybatisHandlerDefault();
      }
      return Singleton.INSTANCE;
    } catch (FileNotFoundException e) {
      log.error("An error has occurred. Please check the message. >>>{}", e.getMessage());
      throw new IllegalStateException(e);
    }
  }

}