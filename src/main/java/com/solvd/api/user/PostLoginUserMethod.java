package com.solvd.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/user/login", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "post/login/request.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostLoginUserMethod extends AbstractApiMethodV2 {

}
