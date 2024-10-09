package com.task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private String name;
    private Manager manager;
}
