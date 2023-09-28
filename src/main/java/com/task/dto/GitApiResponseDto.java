package com.task.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class GitApiResponseDto {

    private String id;

    private String login;

    private String node_id;

    private String avatar_url;

    private String gravatar_id;

    private String url;

    private String html_url;

    private String followers_url;

    private String following_url;

    private String gists_url;

    private String starred_url;

    private String subscriptions_url;

    private String organizations_url;

    private String repos_url;

    private String events_url;

    private String received_events_url;

    private String type;

    private String site_admin;

    private String name;

    private String company;

    private String blog;

    private String location;

    private String email;

    private String hireable;

    private String bio;

    private String twitter_username;

    private int public_repos;

    private int public_gists;

    private int followers;

    private int following;

    private Date created_at;

    private Date updated_at;

}
