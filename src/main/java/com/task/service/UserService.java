package com.task.service;

import com.task.client.GitApiClient;
import com.task.converter.GitApiResponseDtoToUserDto;
import com.task.dao.UserRequestsDao;
import com.task.dto.UserDto;
import com.task.entity.UserRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GitApiClient gitApiClient;
    private final GitApiResponseDtoToUserDto gitApiResponseDtoToUserDto;
    private final UserRequestsDao userRequestsDao;

    public UserDto getUserData(String login) {
        return gitApiResponseDtoToUserDto.mapToUserDto(gitApiClient.getUserData(login));
    }

    @Transactional
    public Optional<UserRequests> saveUserCallOccurenceIfExists(String login) {
        Optional<UserRequests> userRequestsOpt = userRequestsDao.findByLogin(login);

        if (userRequestsOpt.isPresent()) {
            UserRequests userRequests = userRequestsOpt.get();
            userRequests.setRequestCount(userRequests.getRequestCount() + 1);
            userRequestsDao.save(userRequests);
        }

        return userRequestsOpt;
    }

    @Transactional
    public UserRequests insertNewCall(String login) {

        Optional<UserRequests> userRequestsOpt = userRequestsDao.findByLogin(login);

        if (userRequestsOpt.isPresent()) {
            UserRequests userRequests = userRequestsOpt.get();
            userRequests.setRequestCount(userRequests.getRequestCount() + 1);
            userRequestsDao.save(userRequests);
            return userRequests;
        }

        UserRequests userRequests = new UserRequests();
        userRequests.setLogin(login);
        userRequests.setRequestCount(1);
        userRequestsDao.save(userRequests);
        return userRequests;
    }
}