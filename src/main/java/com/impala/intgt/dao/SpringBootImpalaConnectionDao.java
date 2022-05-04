/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.impala.intgt.dao;

import com.impala.intgt.model.TxnObj;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chandanar
 */
@Repository
@Slf4j
public class SpringBootImpalaConnectionDao {

    @Autowired
    DataSource dataSource;

    public void save(TxnObj txnObj) throws SQLException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        this.insertData(connection, txnObj);
    }

    public void insertData(Connection connection, TxnObj txnObj) throws SQLException {

        PreparedStatement pstmnt = null;
        try {
            String sql = "INSERT INTO vas_data.txn_subs" + " (mobileNo, registeredOn, language, actChannel, status, contentName, "
                    + "contentId, dtCode, category, subCategory, cpName, album, billedOn, addedOn) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            pstmnt = connection.prepareStatement(sql);

            pstmnt.setString(1, txnObj.getMobileNo());
            pstmnt.setString(2, txnObj.getRegisteredOn());
            pstmnt.setString(3, txnObj.getLanguage());
            pstmnt.setString(4, txnObj.getActChannel());
            pstmnt.setString(5, txnObj.getStatus());
            pstmnt.setString(6, txnObj.getContentName());
            pstmnt.setString(7, txnObj.getContentId());
            pstmnt.setString(8, txnObj.getDtCode());
            pstmnt.setString(9, txnObj.getCategory());
            pstmnt.setString(10, txnObj.getSubCategory());
            pstmnt.setString(11, txnObj.getCpName());
            pstmnt.setString(12, txnObj.getAlbum());
            pstmnt.setString(13, txnObj.getBilledOn());
            pstmnt.setTimestamp(14, new Timestamp(new Date().getTime()));

            pstmnt.executeUpdate();

        } finally {
            if (pstmnt != null) {
                pstmnt.close();
            }
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }
}