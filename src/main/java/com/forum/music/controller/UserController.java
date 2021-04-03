package com.forum.music.controller;

import com.forum.music.dto.UserDto;
import com.forum.music.services.UserService;
import com.forum.music.utils.ConverterUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Return All Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return All users"),
            @ApiResponse(code = 500, message = "Error in server"),
    })
    @GetMapping("/users")
    public ResponseEntity<Set<UserDto>> listUsers(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ConverterUser.converUsersToSetDTO(userService.list() ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error List User", e);
        }
    }

    @ApiOperation(value = "Add user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Add user send"),
            @ApiResponse(code = 400, message = "Data invalid send"),
    })
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
        try {
            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(ConverterUser.converUserToDTO(userService.create(ConverterUser.convertUserDTOToUser(user) ) ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error Insert User", e);
        }
    }

    @ApiOperation(value = "udpate user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "update user send"),
            @ApiResponse(code = 400, message = "Data invalid send"),
    })
    @PatchMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.OK)
            .body(ConverterUser.converUserToDTO(userService.update(ConverterUser.convertUserDTOToUser(user) ) ) );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error update User", e);
        }
    }

    @ApiOperation(value = "delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "delete user send"),
            @ApiResponse(code = 400, message = "Data invalid send"),
    })
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
