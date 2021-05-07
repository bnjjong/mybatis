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
 * <p> Mybatis Environments {@code Interface} </p>
 *
 * <pre>
 *   {@link org.apache.ibatis.session.SqlSessionFactory} 생성시
 * </pre>
 *
 * @author jordan
 * @version 1.0
 * @see EnvironmentsDefault
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public interface Environments {
  String toName();
}
