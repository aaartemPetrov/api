package com.solvd.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/users/search?q=${searchQuery}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetSearchUserMethod extends AbstractApiMethodV2 {
    public GetSearchUserMethod(String urlSearchQuery) {
        replaceUrlPlaceholder("searchQuery", urlSearchQuery);
    }
}
