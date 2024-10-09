package com.task.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Team {

    private String name;
    private List<Project> departments;


}
