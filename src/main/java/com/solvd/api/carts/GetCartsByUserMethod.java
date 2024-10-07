package com.solvd.api.carts;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/carts/user/${userId}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCartsByUserMethod extends AbstractApiMethodV2 {
    public GetCartsByUserMethod(String userId) {
        replaceUrlPlaceholder("userId", userId);
    }
}
