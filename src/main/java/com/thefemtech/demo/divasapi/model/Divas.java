package com.thefemtech.demo.divasapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "divas")
public class Divas {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Id
    private String id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String job;

    public Divas(String id, @NotBlank @Size(max = 255) String name, @NotBlank @Size(max = 255) String job) {
        this.id = id;
        this.name = name;
        this.job = job;
    }

    public Divas() {
    }
}
