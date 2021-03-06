package com.omoto.configurator.service.Impl;

import com.omoto.configurator.model.User;
import com.omoto.configurator.model.UserRequest;
import com.omoto.configurator.repository.UserRepository;
import com.omoto.configurator.service.UserRepositryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by omoto on 16/12/16.
 */
@Slf4j
@RestController
@RequestMapping("/config")
public class UserRepositryServiceImpl implements UserRepositryService {

    private UserRepository userRepository;

    @Autowired
    public UserRepositryServiceImpl(UserRepository userRepository) {
        Assert.notNull(userRepository, "userRepository cannot be null");

        this.userRepository = userRepository;

    }

    @Override
    public User findByUser(String user) {

        log.info("UserRepositoryServiceImpl.findByUser");

        Assert.hasLength(user, "user is a required attribute!");

        User result = userRepository.findByUser(user);

        return result;

    }

    public void delete(User user) {
        log.info("UserRepositoryServiceImpl.delete");
        userRepository.delete(user);

    }

    public User save(User user) {

        userRepository.save(user);
        return user;
    }


    @RequestMapping(value = "/update", params = {"user"}, method = RequestMethod.PUT, produces = "application/json")
    public int updateProfileData(@RequestParam("user") String user, HttpServletResponse response) {
        log.info("UserRepositoryServiceImpl.updateProfileData");

        Assert.notNull(user, "user is a required attribute!");

        User result = this.findByUser(user);
      /*  try {
            userRepository.updateProfileData(result.getUser(), profKey, profValue);
        }catch (InvalidQueryException e) {
            return -1;
        }*/
        return 0;
    }


    @ApiOperation(value = "addUser", nickname = "addUser", httpMethod = "POST", notes = "Fetch the admin user details", response = Response.class)
    @RequestMapping(method = RequestMethod.POST, value = "adduser", produces = "application/json")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User addUser(@RequestBody UserRequest userRequest) {
        long startTime = System.nanoTime();

        User userObj = new User();
        userObj.setUserName(userRequest.getUserName());
        userObj.setPassword(userRequest.getPassword());

        this.save(userObj);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // Milliseconds

        log.info("TIMING: Time to add a user: "  +  duration + " ms");
       // return  "User created successfully! Time to create new user: " + duration + " ms";
        return  userObj;
    }

}
