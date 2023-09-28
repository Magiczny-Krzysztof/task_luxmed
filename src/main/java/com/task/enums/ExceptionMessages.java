package com.task.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionMessages {

    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPPTION"),
    VALIDATION_FAILED("VALIDATION_FAILED"),
    NOT_FOUND("NOT_FOUND");

    private final String description;

}
