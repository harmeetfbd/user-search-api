package com.user.search.service

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.user.search.client.UserListQueryClient
import com.user.search.model.UserDetail
import feign.Request
import feign.Response
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.nio.charset.Charset

class UserServiceSpec extends Specification {

    UserListQueryClient userListQueryClient

    UserService userService

    def setup() {
        userListQueryClient = Mock(UserListQueryClient.class)
        userService = new UserService(userListQueryClient)
    }

    def "Test to Extract User list from Json Response of Third Party API"() {
        given: "Feign Response created from Mocked UserListQueryClient"
        Response response = createFeignResponse(HttpStatus.OK.value())
        userListQueryClient.query() >> response

        when: "UserService called to extractJsonResponse from API"
        List<UserDetail> userDetails = userService.extractJsonResponse()

        then: "List of Users must be returned"
        userDetails.size() == 2

    }

    def createFeignResponse(int status) {
        return Response.builder().
                status(status)
                .request(Request.create(Request.HttpMethod.GET, "/", prepareHeaders(), Request.Body.empty().asBytes(), Charset.defaultCharset()))
                .body(sampleResponseJson(), Charset.defaultCharset())
                .build()
    }

    Map<String, Collection<String>> prepareHeaders() {
        return new HashMap<String, Collection<String>>() {
            {
                put("Content-Type", Arrays.asList("application/json;charset=UTF-8"))
            }
        }
    }

    def sampleResponseJson() {
        JsonArray jsonArray = new Gson().fromJson(JSON_RESPONSE, JsonArray.class)
        return jsonArray.toString()
    }

    def final JSON_RESPONSE = "[{id: 1, username:'Brat', email: '1@test.com', phone: '001122'}, {id: 2, username:'James', email: '2@test.com', phone: '002211'}]"


}
