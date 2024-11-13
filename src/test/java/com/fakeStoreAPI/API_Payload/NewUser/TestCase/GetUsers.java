package com.fakeStoreAPI.API_Payload.NewUser.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.Config.Databases;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class GetUsers {
    private String loginUsername;
    private String loginPassword;
    @Test
    public void getAllUsers(){
         Response responseOfGetAllUsers=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.ADD_USER)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        //Assertion Part
        int size=responseOfGetAllUsers.path("id.size()");
        Assert.assertEquals( size,10);
        for (int i=0;i<size;i++){
            String usernameGet=responseOfGetAllUsers.path("username["+i+"]");
            Assert.assertEquals(usernameGet, Databases.USERNAME_DATABASE[i]);
        }
        long responseTime=responseOfGetAllUsers.getTimeIn(TimeUnit.MILLISECONDS);
        assert responseTime<2000;
    }
    @Test
    public void getSingleUser(){
        Response responseOfGetSingleUser= given()
                .baseUri(APIResources.BASE_URL)
                .expect().defaultParser(Parser.JSON)
        .when()
                .get(APIResources.ADD_USER+APIResources.USER_ID)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        //usage Data
        loginUsername= responseOfGetSingleUser.path("username");
        loginPassword= responseOfGetSingleUser.path("password");
        System.out.println("login username: "+loginUsername);
        System.out.println("login password: "+loginPassword);
        //Assertion Part
        int size=responseOfGetSingleUser.path("id.size()");
        Assert.assertEquals( size,1);
        int firstId=responseOfGetSingleUser.path("id[0]");
        Assert.assertEquals(firstId,1);
        long responseTime=responseOfGetSingleUser.getTimeIn(TimeUnit.MILLISECONDS);
        assert responseTime<2000;
    }
    @Test
    public void sortedGetUsers(){
        Response responseOfSortedGetUsers =given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("limit",5)
                .queryParam("sort","desc")
        .when()
                .get(APIResources.ADD_USER)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        //Assertion Part
        int size=responseOfSortedGetUsers.path("id.size()");
        Assert.assertEquals( size,5);
        int firstId=responseOfSortedGetUsers.path("id[0]");
        Assert.assertEquals(firstId,10);
        long responseTime=responseOfSortedGetUsers.getTimeIn(TimeUnit.MILLISECONDS);
        assert responseTime<2000;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }
}
