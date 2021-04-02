package com.forum.music.controller;

import com.forum.music.dto.UserDto;
import com.forum.music.services.UserService;
import com.forum.music.utils.ConverterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Set<UserDto>> listUsers(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ConverterUser.converUsersToSetDTO(userService.list() ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error List User", e);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
        try {
            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(ConverterUser.converUserToDTO(userService.create(ConverterUser.convertUserDTOToUser(user) ) ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error Insert User", e);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.OK)
            .body(ConverterUser.converUserToDTO(userService.update(ConverterUser.convertUserDTOToUser(user) ) ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error update User", e);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> delete(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ConverterUser.converUserToDTO(userService.delete(userService.create(ConverterUser.convertUserDTOToUser(user) ) ) ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error Insert User", e);
        }
    }

}                    
