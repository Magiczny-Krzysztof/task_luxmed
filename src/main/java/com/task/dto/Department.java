package com.task.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Department {

    private String name;
    private List<Team> teams;

}
