package com.dagnerchuman.springbootmicroservice3ApiGateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void makeColumnNullable(String columnName) {
        String sql = "ALTER TABLE users MODIFY " + columnName + " VARCHAR(255) NULL";
        jdbcTemplate.execute(sql);
    }
}
