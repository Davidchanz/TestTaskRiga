package com.gn.testtaskriga.mapper.user;

import com.gn.testtaskriga.dto.user.UserDto;
import com.gn.testtaskriga.model.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User userDtoToUser(UserDto userDto);
}
