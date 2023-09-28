package com.task.client;

import com.task.dto.GitApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "git-api-client", url = "${git.client.api.url}")
public interface GitApiClient {

    @GetMapping(value = "/users/{login}")
    GitApiResponseDto getUserData(@PathVariable String login);
}
