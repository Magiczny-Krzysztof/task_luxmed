package com.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_REQUESTS")
public class UserRequests implements Serializable {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private long requestCount;

}
