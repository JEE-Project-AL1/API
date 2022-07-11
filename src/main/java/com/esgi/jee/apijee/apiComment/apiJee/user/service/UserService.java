package com.esgi.jee.apijee.apiComment.apiJee.user.service;


import com.esgi.jee.apijee.apiComment.apiJee.user.Dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);
}
