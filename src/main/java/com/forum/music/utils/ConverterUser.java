package com.forum.music.utils;

import com.forum.music.dto.ProfileType;
import com.forum.music.dto.UserDto;
import com.forum.music.entity.User;
import com.forum.music.entity.UserProfile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterUser {

    public static UserDto converUserToDTO(User user){

        return  new UserDto(user.getName(), user.getPassword(),
                getType(user.getUserProfile().getProfile()), user.getId(), user.getNickName(), user.getEmail());
    }

    private static String getType(String userProfile) {
        return String.valueOf(ProfileType.ADMIN.ordinal()).equals(userProfile) ? ProfileType.ADMIN.name() : ProfileType.USER.name();
    }
    private static Long getTypeId(String userProfile) {
        return Long.valueOf(ProfileType.ADMIN.name().equals(userProfile) ? ProfileType.ADMIN.ordinal() : ProfileType.USER.ordinal());
    }

    public static Set<UserDto> converUsersToSetDTO(List<User> users){
        Set<UserDto> userDtos = new HashSet<>();
        users.forEach(x -> userDtos.add(converUserToDTO(x)));
        return userDtos;
    }
    public static User convertUserDTOToUser(UserDto userDto){
      return User.builder().name(userDto.name())
                            .password(userDto.password())
                            .userProfile(UserProfile.builder().id(getTypeId(userDto.profileType())).build())
                            .id(userDto.id()==null ? null : userDto.id())
                            .nickName(userDto.ninkName())
                            .email(userDto.email()).build();
    }
}
