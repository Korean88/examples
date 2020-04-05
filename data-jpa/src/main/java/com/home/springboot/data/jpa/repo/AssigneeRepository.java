package com.home.springboot.data.jpa.repo;

import com.home.springboot.data.jpa.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}
