/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.impala.intgt;

import avro.shaded.com.google.common.collect.ImmutableMap;
import com.sun.security.auth.module.Krb5LoginModule;
import java.io.IOException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chandanar
 */

@Configuration
@Slf4j
public class AppConfig {
    
    @Value("${bigdata.impala.connectionURL}")
    private String connectionURL;
    
    @Value("${bigdata.impala.keytab}")
    private String keytab;
    
    @Value("${bigdata.impala.principal}")
    private String principal;
    
    @Bean
    public DataSource dataSource() throws IOException {
        javax.security.auth.login.Configuration jaasConfig = createJaasConfig();
        javax.security.auth.login.Configuration.setConfiguration(jaasConfig);
        System.setProperty("java.security.auth.login.config", jaasConfig.toString());

        com.cloudera.impala.jdbc.DataSource dataSource =
                new com.cloudera.impala.jdbc.DataSource();
        dataSource.setURL(connectionURL);

        return dataSource;
    }
    
    private javax.security.auth.login.Configuration createJaasConfig() {

        log.info("Loading keytab from: " + keytab);

        // Create entry options.
        ImmutableMap<String, String> options = ImmutableMap.of(
                "com.sun.security.auth.module.Krb5LoginModule", "required",
                "doNotPrompt", "true",
                "useKeyTab", "true",
                "keyTab", "" + keytab,
                "principal", "" + principal
        );

        // Create entries.
        final AppConfigurationEntry[] entries = {
            new AppConfigurationEntry(
            Krb5LoginModule.class.getCanonicalName(),
            AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
            options)
        };

        // Create configuration.
        return new javax.security.auth.login.Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                return entries;
            }
        };

    }
}
