/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/05/07
 */

package org.bnjjong.mybatis;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;

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
public class SessionManager {
  private final Map<String, SqlSessionFactory> sessionFactoryMap;

  SessionManager() {
    this.sessionFactoryMap = new HashMap<>();
  }


  /**
   *
   * @param configName
   * @param sqlSessionFactory
   */
  void add(String configName, SqlSessionFactory sqlSessionFactory) {
    this.sessionFactoryMap.put(configName, sqlSessionFactory);
  }

  /**
   *
   * @param configName
   * @return
   */
  SqlSessionFactory get(String configName) {
    if(!sessionFactoryMap.containsKey(configName)) {
      throw new SessionNotFoundException();
    }
    return sessionFactoryMap.get(configName);
  }
}
