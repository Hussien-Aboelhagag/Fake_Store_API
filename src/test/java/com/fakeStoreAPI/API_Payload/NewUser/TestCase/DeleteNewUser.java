package com.fakeStoreAPI.API_Payload.NewUser.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class DeleteNewUser {
    int id=new AddNewUser().getId();
    @Test
    public void deleteNewUser(){
        Response response= given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
        .when()
                .log().all()
                .delete(APIResources.ADD_USER +"/"+id)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
}
