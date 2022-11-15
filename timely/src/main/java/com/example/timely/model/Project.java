package com.example.timely.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_project;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private String start;

    @Column(name = "stop")
    private String stop;

    @Column(name = "duration")
    private String duration;

    public Project() {
    }

    public Project(String name, String start, String stop, String duration) {
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.duration = duration;
    }

    public long getId() {
        return this.id_project;
    }

    public String getName() {
        return this.name;
    }

    public String getStart() {
        return this.start;
    }

    public String getStop() {
        return this.stop;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
