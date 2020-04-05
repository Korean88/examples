package com.home.springboot.data.jpa.repo;

import com.home.springboot.data.jpa.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
