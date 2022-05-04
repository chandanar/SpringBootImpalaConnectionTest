/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.impala.intgt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author chandanar
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TxnObj {
    
    private String mobileNo;
    private String registeredOn;
    private String language;
    private String actChannel;
    private String status;
    private String contentName;
    private String contentId;
    private String dtCode;    
    private String category;      
    private String subCategory;  
    private String cpName;      
    private String album;      
    private String billedOn; 
    
}
