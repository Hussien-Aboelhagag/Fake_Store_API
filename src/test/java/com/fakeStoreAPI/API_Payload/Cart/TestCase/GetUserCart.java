package com.fakeStoreAPI.API_Payload.Cart.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.fakeStoreAPI.Config.Databases.readJsonFile;
import static io.restassured.RestAssured.given;

public class GetUserCart {
    @Test
    public void sortedGetCartBYDataRange(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("limit",5)
                .queryParam("startdate","2019-12-30")
                .queryParam("enddate","2020-10-10")
        .when()
                .get(APIResources.ADD_CART)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,5);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<3000;

    }
    @Test
    public void sortedGetCartByInvalidDataRange(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("limit",5)
                .queryParam("startdate","2019-12-50")
                .queryParam("enddate","2020-10-10")
        .when()
                .get(APIResources.ADD_CART)
        .then()
                .log().all()
                .assertThat().statusCode(400)
                .extract().response();
        String msg=response.path("message");
        String statusBody=response.path("status");
        Assert.assertEquals(statusBody,"error");
        Assert.assertTrue(msg.contains("date format is not correct"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<3000;
    }
    @Test
    public void getUserCart(){
        String jsonFilePath = "JSON_FILES"+ File.separator+"Get_User_Cart.json";
        String expectedJson = readJsonFile(jsonFilePath);
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.ADD_CART+APIResources.GET_USER_CART+APIResources.USER_ID)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        expectedJson=expectedJson.replaceAll("\\r\\n?", "\n");
        String actualJson=response.body().prettyPrint().replaceAll("\\r\\n?", "\n");
        Assert.assertEquals(actualJson,expectedJson);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
}
