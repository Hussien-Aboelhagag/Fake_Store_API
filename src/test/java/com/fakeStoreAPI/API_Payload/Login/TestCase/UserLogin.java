package com.fakeStoreAPI.API_Payload.Login.TestCase;

import com.fakeStoreAPI.API_Payload.NewUser.TestCase.GetUsers;
import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.UserAuth;
import com.fakeStoreAPI.Utils.UserUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserLogin {
    UserAuth userAuth;
    @Test
    public void userCanLogin(){
        String username=new GetUsers().getLoginUsername();
        String password=new GetUsers().getLoginPassword();
        userAuth=new UserAuth(username,password);
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(userAuth)
        .when()
                .post(APIResources.USER_LOGIN)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String token=response.path("token");
        System.out.println(token);
    }
    @Test
    public void userLoginWithInvalidData(){
       userAuth=new UserUtils().auth();
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(userAuth)
        .when()
                .log().all()
                .post(APIResources.USER_LOGIN)
        .then()
                .log().all()
                .assertThat().statusCode(401)
                .extract().response();
        String responseBody = response.body().asString();
        Assert.assertTrue(responseBody.contains("username or password is incorrect"));
    }
}
