package com.fakeStoreAPI.API_Payload.NewUser.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.AddUser;
import com.fakeStoreAPI.Utils.UserUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewUser {
    private int id;
    private String addNewUsername;
    @Test
    public void addNewUser(){
        AddUser addUser=new UserUtils().addUser();
        Response addUserResponse= given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(addUser)
        .when()
                .log().all()
                .post(APIResources.ADD_USER)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        // extract response
        id=addUserResponse.path("id");
        addNewUsername=addUser.getUsername();
    }

    public int getId() {
        return id;
    }

    public String getAddNewUsername() {
        return addNewUsername;
    }

}
