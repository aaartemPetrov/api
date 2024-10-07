package com.solvd.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/user/me", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "get/authenticated_user/request.json")
@ResponseTemplatePath(path = "get/authenticated_user/response.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetAuthenticatedUserMethod extends AbstractApiMethodV2 {

}
