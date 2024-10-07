package com.solvd.api.product;


import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/products/add", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "post/product/request.json")
@ResponseTemplatePath(path = "post/product/response.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostNewProductMethod extends AbstractApiMethodV2 {
}
