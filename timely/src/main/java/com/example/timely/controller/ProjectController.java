package com.example.timely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import com.example.timely.repository.ProjectRepository;
import com.example.timely.model.Project;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("timely")
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projects = new ArrayList<Project>();

            projectRepository.findAll().forEach(projects::add);

            if (projects.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("newProject")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        try {
            Project newProject = projectRepository
                    .save(new Project(project.getName(), project.getStart(), project.getStop(), project.getDuration()));
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/stop/{id}")
    public ResponseEntity<Project> addName(@PathVariable("id") String id, @RequestBody String name) {
        try {

            Long Id = Long.parseLong(id);
            Optional<Project> projectData = projectRepository.findById(Id);
            
            if (projectData.isPresent()) {
                Project _project = projectData.get();
                _project.setName(name);
                Project novi = projectRepository.save(_project);
                return new ResponseEntity<>(novi, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") String id) {
        try {
            Long Id = Long.parseLong(id);
            projectRepository.deleteById(Id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            projectRepository.deleteAll();
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") String id) {
        try {

            Long Id = Long.parseLong(id);
            Optional<Project> projectData = projectRepository.findById(Id);
            Project _project = projectData.get();

            return new ResponseEntity<>(_project, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") String id, @RequestBody Project project) {
        try {
            Long Id = Long.parseLong(id);
            Optional<Project> projectData = projectRepository.findById(Id);
            Project _project = projectData.get();
            _project.setName(project.getName());
            _project.setStart(project.getStart());
            _project.setStop(project.getStop());
            _project.setDuration(project.getDuration());

            return new ResponseEntity<>(projectRepository.save(_project), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
