package com.task.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserDto {

    private String id;

    private String login;

    private String name;

    private String type;

    private String avatarUrl;

    private Date createdAt;

    private String calculations;
}
