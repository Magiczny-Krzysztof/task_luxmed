package com.task.converter;

import com.task.dto.GitApiResponseDto;
import com.task.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.stream.Stream;


public abstract class GitApiResponseDtoToUserDtoDecorator
        implements GitApiResponseDtoToUserDto {

    @Value("${git.static.val1}")
    private double val1;

    @Value("${git.static.val2}")
    private double val2;

    @Autowired
    @Qualifier("delegate")
    protected GitApiResponseDtoToUserDto delegate;

    @Override
    public UserDto mapToUserDto(GitApiResponseDto gitApiResponseDto) {
        UserDto userDto = delegate.mapToUserDto(gitApiResponseDto);

        boolean result = Stream.of(gitApiResponseDto.getFollowers(), gitApiResponseDto.getPublic_repos())
                .allMatch(val -> val > 0);

        if (result) {

            BigDecimal bigDecimal = new BigDecimal((val1 / gitApiResponseDto.getFollowers() * (val2 / gitApiResponseDto.getPublic_repos())));

            userDto.setCalculations(bigDecimal.toPlainString());
        }

        return userDto;
    }
}
