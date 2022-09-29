package com.user.search.controller;

import com.user.search.model.UserDetail;
import com.user.search.model.UserSearchResponse;
import com.user.search.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is a Rest controller to expose the user search Api
 */
@Slf4j
@RestController
@Tag(name = "UserSearchService", description = "Provides User administration")
public class UserSearchController {

    public static final String DEFAULT_ID = "-1";

    private final UserService userService;

    public UserSearchController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/getusercontacts/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "get", summary = "fetch user record by userId Or by username, in case of both provided, User id will take precedence",
            parameters = {@Parameter(in = ParameterIn.QUERY, name = "userid", description = "user id to search", required = false),
                    @Parameter(in = ParameterIn.QUERY, name = "username", description = "user name to search", required = false)})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "record found successfully", content = @Content(schema = @Schema(implementation = UserSearchResponse.class))),
            @ApiResponse(responseCode = "500", description = "something bad happened. please contact service provider", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<UserSearchResponse> findUserByIdOrName(@RequestParam(value = "userid", required = false) Integer userid,
                                                                 @RequestParam(value = "username", required = false) String username) {

        UserSearchResponse response;
        if (userid != null && userid > 0) {
            log.info("User Search requested with userid [{}]", userid);
            response = getUserDetailById(userid);
        } else if (username != null && !username.isEmpty()) {
            log.info("User Search requested with username [{}]", username);
            response = getUserDetailByUserName(username);
        } else {
            log.info("User Search requested without any parameters");
            response = createDefaultUserResponse();
        }
        log.info("User searched with details [{}]", response.toString());
        return ResponseEntity.ok().body(response);
    }


    private UserSearchResponse getUserDetailById(int userid) {
        List<UserDetail> userList = userService.extractJsonResponse();
        return userList.stream().
                filter(user -> user.getId().equals(userid)).
                findFirst().map(this::getUserSearchResponse).orElse(createDefaultUserResponse());
    }


    private UserSearchResponse getUserDetailByUserName(String username) {
        List<UserDetail> userList = userService.extractJsonResponse();
        return userList.stream().
                filter(user -> user.getUsername().equalsIgnoreCase(username)).
                findFirst().map(this::getUserSearchResponse).orElse(createDefaultUserResponse());
    }

    private UserSearchResponse createDefaultUserResponse() {
        UserSearchResponse userSearchResponse = new UserSearchResponse();
        userSearchResponse.setId(Integer.valueOf(DEFAULT_ID));
        return userSearchResponse;
    }

    private UserSearchResponse getUserSearchResponse(UserDetail userDetail) {
        UserSearchResponse userSearchResponse = new UserSearchResponse();
        userSearchResponse.setId(userDetail.getId());
        userSearchResponse.setEmail(userDetail.getEmail());
        userSearchResponse.setPhone(userDetail.getPhone());
        return userSearchResponse;
    }


}