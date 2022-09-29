package com.user.search.controller


import com.user.search.model.UserDetail
import com.user.search.model.UserSearchResponse
import com.user.search.service.UserService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

/**
 * Test specification for UserSearchController
 */
class UserSearchControllerSpec extends Specification {


    UserService userService

    UserSearchController userSearchController

    def setup() {
        userService = Mock(UserService.class)
        userSearchController = new UserSearchController(userService);
    }


    def "Test when User search with UserId"() {
        given: "UserId to be searched, sample response created for Third party API"

        userService.extractJsonResponse() >> sampleUserList()

        when: "Response UserSearchResponse Json will be returned when findUserByIdOrName"
        ResponseEntity<UserSearchResponse> userSearchResponse = userSearchController.findUserByIdOrName(1, null)

        then: "User is searched successfully with request param of valid UserId"
        userSearchResponse.body.getId() == 1
        userSearchResponse.body.getEmail() == "1@test.com"
    }

    def "Test when User search with UserName"() {
        given: "UserName to be searched, sample response created for Third party API"
        userService.extractJsonResponse() >> sampleUserList()

        when: "Response UserSearchResponse Json will be returned when findUserByIdOrName"
        ResponseEntity<UserSearchResponse> userSearchResponse = userSearchController.findUserByIdOrName(null, "James")

        then: "User is searched successfully with request param of valid UserName"
        userSearchResponse.body.getId() == 2
        userSearchResponse.body.getEmail() == "2@test.com"
    }

    def "Test when User search with UserId which does not Exist"() {
        given: "UserName to be searched, sample response created for Third party API"
        userService.extractJsonResponse() >> sampleUserList()

        when: "Response UserSearchResponse Json will be returned when findUserByIdOrName"
        ResponseEntity<UserSearchResponse> userSearchResponse = userSearchController.findUserByIdOrName(3, null)

        then: "Default User with Id -1 will be returned"
        userSearchResponse.body.getId() == -1
        userSearchResponse.body.getEmail() == null
    }

    private List<UserDetail> sampleUserList() {

        UserDetail userDetailOne = new UserDetail()
        userDetailOne.setId(1);
        userDetailOne.setUsername("Brat")
        userDetailOne.setEmail("1@test.com")
        userDetailOne.setPhone("001122")

        UserDetail userDetailTwo = new UserDetail();
        userDetailTwo.setId(2);
        userDetailTwo.setUsername("James")
        userDetailTwo.setEmail("2@test.com")
        userDetailTwo.setPhone("002211")

        return List.of(userDetailOne, userDetailTwo)


    }

}
