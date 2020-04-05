package com.home.springboot.data.jpa.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTestUtil {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTestUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void insertProject(int id, String name) {
        jdbcTemplate.update("insert into project values(?, ?)", id, name);
    }

    void insertAssignee(int id, String fullName, int projectId) {
        jdbcTemplate.update("insert into assignee values(?, ?, ?)", id, fullName, projectId);
    }

}
