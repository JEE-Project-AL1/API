package com.esgi.jee.apijee.apiComment.apiJee.user.service;

import com.esgi.jee.apijee.apiComment.apiJee.user.Dto.UserDto;
import com.esgi.jee.apijee.apiComment.apiJee.user.domain.User;
import com.esgi.jee.apijee.apiComment.apiJee.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public DefaultUserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User newUser = this.userRepository.save(user);
        return mapToDto(newUser);
    }

    @Override
    public UserDto getUserById(long id) {
        User user = this.userRepository.getById(id);
        return mapToDto(user);
    }


    private UserDto mapToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    private User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
}
