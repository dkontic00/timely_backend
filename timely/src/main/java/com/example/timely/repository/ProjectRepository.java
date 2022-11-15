package com.example.timely.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.timely.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
