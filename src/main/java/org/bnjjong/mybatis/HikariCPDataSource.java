/*
 * COPYRIGHT (c) ADOP 2020
 * This software is the proprietary of ADOP
 *
 * @author <a href=“mailto:jordan@adop.cc“>jordan</a>
 * @since 2020/09/07
 */

package org.bnjjong.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * create on 2020-02-17.
 * <p> Hikari Pool </p>
 * <p> UnpooledDataSourceFactory를 통해 또다른 DataSource 생성</p>
 *
 * @author jwpark
 * @version 1.0
 * @see UnpooledDataSourceFactory
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public class HikariCPDataSource extends UnpooledDataSourceFactory {

  public HikariCPDataSource() {
    HikariConfig config = new HikariConfig("/properties/hikari.pool.properties");
    this.dataSource = new HikariDataSource(config);
  }
}
