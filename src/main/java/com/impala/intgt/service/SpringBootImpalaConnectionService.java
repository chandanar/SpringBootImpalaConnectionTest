/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.impala.intgt.service;

import com.impala.intgt.dao.SpringBootImpalaConnectionDao;
import com.impala.intgt.model.TxnObj;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chandanar
 */

@Service
@Slf4j
public class SpringBootImpalaConnectionService {
    
    @Autowired
    private SpringBootImpalaConnectionDao springBootImpalaConnectionDao;
    
    public void insertTxn(TxnObj txnObj) throws SQLException {
        springBootImpalaConnectionDao.save(txnObj);
        log.info("Txn {} saved.", txnObj.getMobileNo());
    }
}
