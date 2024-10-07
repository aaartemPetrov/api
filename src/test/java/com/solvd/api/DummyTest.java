package com.solvd.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.solvd.api.carts.GetAllCartsMethod;
import com.solvd.api.carts.GetCartsByUserMethod;
import com.solvd.api.product.DeleteProductMethod;
import com.solvd.api.product.PatchProductMethod;
import com.solvd.api.product.PostNewProductMethod;
import com.solvd.api.user.GetAllUsersMethod;
import com.solvd.api.user.GetAuthenticatedUserMethod;
import com.solvd.api.user.GetSearchUserMethod;
import com.solvd.api.user.GetSingleUserMethod;
import com.solvd.api.user.PostLoginUserMethod;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.core.IAbstractTest;

import io.restassured.response.Response;

public class DummyTest implements IAbstractTest {

    @Test
    public void getAllUsersTest() {
        GetAllUsersMethod getAllUsersMethod = new GetAllUsersMethod();
        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<JSONArray>withPredicate("arrayPredicate", array -> array.length() == 30);
        getAllUsersMethod.callAPIExpectSuccess();
        getAllUsersMethod.validateResponse(comparatorContext);
    }

    @Test
    public void loginAndGetAuthenticatedUserTest() {
        String username = "emilys";
        String password = "emilyspass";
        PostLoginUserMethod loginUserMethod = new PostLoginUserMethod();
        loginUserMethod.addProperty("username", username);
        loginUserMethod.addProperty("password", password);
        Response response = loginUserMethod.callAPIExpectSuccess();
        String accessToken = response.jsonPath().getString("accessToken");
        GetAuthenticatedUserMethod getAuthenticatedUserMethod = new GetAuthenticatedUserMethod();
        getAuthenticatedUserMethod.setHeaders(String.format("Authorization=%s", "Bearer " + accessToken));
        getAuthenticatedUserMethod.addProperty("username", username);
        getAuthenticatedUserMethod.addProperty("password", password);
        getAuthenticatedUserMethod.callAPIExpectSuccess();
        getAuthenticatedUserMethod.validateResponse();
    }

    @Test
    public void getSearchedUserCartTest() {
        GetAllCartsMethod getAllCartsMethod = new GetAllCartsMethod();
        getAllCartsMethod.callAPIExpectSuccess();
        Response response = getAllCartsMethod.callAPI();
        List<Map> allCarts = response.jsonPath().getList("carts");

        SoftAssert softAssert = new SoftAssert();
        for (Map cart: allCarts) {
            String userId = cart.get("userId").toString();
            GetSingleUserMethod getSingleUserMethod = new GetSingleUserMethod(userId);
            String searchQuery = getSingleUserMethod.callAPIExpectSuccess().jsonPath().getString("firstName");

            GetSearchUserMethod getSearchUserMethod = new GetSearchUserMethod(searchQuery);
            response = getSearchUserMethod.callAPIExpectSuccess();
            List<Map> searchedUsers = response.jsonPath().getList("users");
            String searchedUserId = searchedUsers.stream()
                    .filter(entry -> searchQuery.equals(entry.get("firstName")))
                    .filter(entry ->  userId.equals(entry.get("id").toString()))
                    .map(entry -> entry.get("id"))
                    .findFirst().get().toString();

            GetCartsByUserMethod getCartsByUserMethod = new GetCartsByUserMethod(searchedUserId);
            response = getCartsByUserMethod.callAPIExpectSuccess();
            List<Map> cartsByUser = response.jsonPath().getList("carts");
            List<String> userIds = cartsByUser.stream()
                    .map(entry -> entry.get("userId").toString()).collect(Collectors.toList());
            softAssert.assertTrue(userIds.stream().allMatch(item -> searchedUserId.equals(item) && userId.equals(item)), "Something is wrong with user. userId: " + userId);
        }
        softAssert.assertAll();
    }


    @Test
    public void postProductTest() {
        PostNewProductMethod postNewProductMethod = new PostNewProductMethod();
        postNewProductMethod.callAPIExpectSuccess();
        postNewProductMethod.validateResponse();
    }

    @Test
    public void updateProductTest() {
        String title = "I love Alla!!!";
        String id = "5";
        PatchProductMethod patchProductMethod = new PatchProductMethod(id);
        patchProductMethod.addProperty("productTitle", title);
        patchProductMethod.callAPIExpectSuccess();
        patchProductMethod.validateResponse();
    }

    @Test
    public void deleteProductTest() {
        String id = "5";
        DeleteProductMethod deleteProductMethod = new DeleteProductMethod(id);
        deleteProductMethod.addProperty("productId", Integer.parseInt(id));
        deleteProductMethod.callAPIExpectSuccess();
        deleteProductMethod.validateResponse();
    }

}
