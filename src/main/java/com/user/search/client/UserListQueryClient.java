package com.user.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign client to query List of users from Third party API
 */
@FeignClient(name = "UserListQueryClient", url = "${user.list.query.url}")
public interface UserListQueryClient {

    @RequestMapping(method = RequestMethod.GET)
    feign.Response query();
}
