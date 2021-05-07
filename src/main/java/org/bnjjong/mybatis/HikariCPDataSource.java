/*
 * Created By dogfootmaster@gmail.com on 2020
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>jordan</a>
 * @since 2020/12/16
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
 * @author bnjjong
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
