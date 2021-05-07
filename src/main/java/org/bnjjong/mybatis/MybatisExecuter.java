/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>jordan</a>
 * @since 2020/12/16
 */

package org.bnjjong.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * create on 2020/09/09.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author jordan
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */

public interface MybatisExecuter {
    static <E> List<E> selectList(SqlSession session, String statement, Object parameter, boolean isSesseionClosed) {

        try {
            return session.selectList(statement, parameter);
        } finally {
            if(isSesseionClosed){
                session.close();
            }
        }

    }
    static <E> List<E> selectList(SqlSession session, String statement, Object parameter) {
        return selectList(session, statement, parameter, true);
    }


    static <T> T selectOne(SqlSession session, String statement, Object parameter, boolean isSesseionClosed) {
        try {
            return session.selectOne(statement, parameter);
        } finally {
            if(isSesseionClosed){
                session.close();
            }
        }
    }
    static <T> T selectOne(SqlSession session, String statement, Object parameter) {
        return selectOne(session, statement, parameter, true);
    }

    static int insert(SqlSession session, String statement, Object parameter) {
        return insert(session, statement, parameter, true);
    }

    static int insert(SqlSession session, String statement, Object parameter, boolean isSesseionClosed) {
        try {
            return session.insert(statement, parameter);
        } finally {
            if(isSesseionClosed){
                session.close();
            }
        }
    }

    static int update(SqlSession session, String statement, Object parameter) {
        return update(session, statement, parameter, true);
    }

    static int update(SqlSession session, String statement, Object parameter, boolean isSesseionClosed) {
        try {
            return session.update(statement, parameter);
        } finally {
            if(isSesseionClosed){
                session.close();
            }
        }
    }

    static int delete(SqlSession session, String statement, Object parameter) {
        return delete(session, statement, parameter, true);
    }

    static int delete(SqlSession session, String statement, Object parameter, boolean isSesseionClosed) {
        try {
            return session.delete(statement, parameter);
        } finally {
            if(isSesseionClosed){
                session.close();
            }
        }
    }
}
