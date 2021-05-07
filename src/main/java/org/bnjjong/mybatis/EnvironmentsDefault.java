/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>jordan</a>
 * @since 2020/12/16
 */

package org.bnjjong.mybatis;

/**
 * create on 2020/12/16. create by IntelliJ IDEA.
 *
 * <p> Mybatis 에서 사용하느 Environment Enum 클래스 </p>
 *
 * @author jordan
 * @version 1.0
 * @see Environments
 * @since 14+
 */
public enum EnvironmentsDefault implements Environments{
  /**
   * 샘플로 생성
   */
  DEFAUlT {
    @Override
    public String toName() {
      return "default";
    }
  },
  EMPTY {
    @Override
    public String toName() {
      return "";
    }
  }
}
