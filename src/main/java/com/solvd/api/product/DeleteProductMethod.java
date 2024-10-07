package com.solvd.api.product;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/products/${productId}", methodType = HttpMethodType.DELETE)
@ResponseTemplatePath(path = "delete/product/response.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeleteProductMethod extends AbstractApiMethodV2 {
    public DeleteProductMethod(String productId) {
        replaceUrlPlaceholder("productId", productId);
    }
}
