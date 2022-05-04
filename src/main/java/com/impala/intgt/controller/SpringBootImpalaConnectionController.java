/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.impala.intgt.controller;

import com.impala.intgt.model.TxnObj;
import com.impala.intgt.service.SpringBootImpalaConnectionService;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chandanar
 */


@RequestMapping("/api/bigdata")
@RestController
@Slf4j
public class SpringBootImpalaConnectionController {
    
    @Autowired
    private SpringBootImpalaConnectionService springBootImpalaConnectionService;
    
    @PostMapping(path = "/txn", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void insertTxn(@RequestBody TxnObj txnObj) {
        try {
            log.info(txnObj.toString());
            springBootImpalaConnectionService.insertTxn(txnObj);
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }
    
    @GetMapping("/test")
    public String test() {
        return "It's Done";
    }
    

    
}
