package com.solvd.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/users", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "get/users/response.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetAllUsersMethod extends AbstractApiMethodV2 {
}
