package com.amarmodi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("I AM PUSGING TO TEHET DBBBB");
        jdbcTemplate.execute("Create table if not exists INPUT_POSTS_FAKE ( id numeric ,name varchar(255));");
        jdbcTemplate.execute("Create table if not exists COUNTRY_FAKE (country_i serial,name text, country_code text, population numeric, crte_ts timestamp default now());");
//        SELECT version();
        String s = jdbcTemplate.queryForObject("SELECT version();", String.class);
        System.out.println("The version of this db is " + s);
    }
}
