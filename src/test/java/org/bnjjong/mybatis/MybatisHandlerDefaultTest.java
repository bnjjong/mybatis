/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>bnjjong</a>
 * @since 2020/12/23
 */

package org.bnjjong.mybatis;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 2020/12/23. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author bnjjong
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Slf4j
class MybatisHandlerDefaultTest {

  @BeforeAll
  static void init() {
    MybatisHandler instance = MybatisFactory.getInstance();
    SqlSession session = instance.getSession();
    MybatisExecuter.update(session, "test.createTestTable", null);
  }

  @AfterAll
  static void finish() {
    MybatisHandler instance = MybatisFactory.getInstance();
    SqlSession session = instance.getSession();
    MybatisExecuter.update(session, "test.dropTestTable", null);
  }

  @Test
  @DisplayName("인스턴스 생성 테스트")
  void test01() {
    // when
    MybatisHandler instance = MybatisFactory.getInstance();

    // then
    assertThat(instance).isNotNull();
  }

  @Test
  @DisplayName("insert 테스트")
  void test02() {
    // given
    MybatisHandler instance = MybatisFactory.getInstance();
    SqlSession session = instance.getSession();
    Map<String,Object> map = new HashMap<>();
    int id = 1;
    map.put("id", id);
    map.put("col1", "sample value");
    map.put("col2", "test value");

    // when
    MybatisExecuter.insert(session, "test.insertTest", map, false);

    // then
    Map<String, String> result = MybatisExecuter.selectOne(session, "test.selectTest", id);

    // debug
    for (String key : result.keySet()) {
      log.info("key >>>>> {}", key);
      log.info("value >>>>> {}", result.get(key));
    }

    String col1Value = result.get("COL1");
    String col2Value = result.get("COL2");

    assertThat(col1Value).isEqualTo("sample value");
    assertThat(col2Value).isEqualTo("test value");
  }
}