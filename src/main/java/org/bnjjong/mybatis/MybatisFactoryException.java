/*
 * COPYRIGHT (c) ADOP 2020
 * This software is the proprietary of ADOP
 *
 * @author <a href=“mailto:jordan@adop.cc“>jordan</a>
 * @since 2020/09/07
 */
package org.bnjjong.mybatis;

/**
 * create on 2020/09/07.
 * <p> {@link MybatisFactory} 에서 발생하는 예외 처리 </p>
 *
 * @author jordan
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public class MybatisFactoryException extends RuntimeException{
    public MybatisFactoryException(String message) {
        super(message);
    }
}
