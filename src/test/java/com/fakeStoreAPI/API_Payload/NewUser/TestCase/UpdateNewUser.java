package com.fakeStoreAPI.API_Payload.NewUser.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.AddUser;
import com.fakeStoreAPI.Utils.UserUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class UpdateNewUser {
    int id=new AddNewUser().getId();
    String updateNewUsername;
    String newUserName= new AddNewUser().getAddNewUsername();
    @Test
    public void updateUser(){
    AddUser addUser=new UserUtils().addUser();

        Response response=given()
            .baseUri(APIResources.BASE_URL)
            .header("Content-Type","application/json")
            .body(addUser)
        .when()
            .log().all()
            .put(APIResources.ADD_USER +"/"+id)
        .then()
            .log().all()
            .assertThat().statusCode(200)
            .extract().response();
        updateNewUsername=addUser.getUsername();
        String actualUsername=response.path("username");
        Assert.assertEquals(actualUsername,updateNewUsername);
        Assert.assertFalse(updateNewUsername.equals(newUserName));
        String header=response.headers().get("Content-Type").getValue();
        Assert.assertTrue(header.contains("application/json"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
}