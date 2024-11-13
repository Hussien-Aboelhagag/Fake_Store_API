package com.fakeStoreAPI.API_Payload.Cart.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.fakeStoreAPI.Config.Databases.readJsonFile;
import static io.restassured.RestAssured.given;

public class GetCart {
    @Test
    public void getAllCarts(){
        String jsonFilePath = "JSON_FILES"+ File.separator+"Get_All_Carts.json";
        String expectedJson = readJsonFile(jsonFilePath);
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.ADD_CART)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,7);
        String actual=response.body().prettyPrint().replaceAll("\\r\\n?", "\n");
        expectedJson = expectedJson.replaceAll("\\r\\n?", "\n");
        Assert.assertEquals(actual,expectedJson);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<3000;
    }
    @Test
    public void getSingleCart(){
        String jsonFilePath = "JSON_FILES"+ File.separator+"First_Cart.json";
        String expectedJson = readJsonFile(jsonFilePath);
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.ADD_CART+APIResources.USER_ID)
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
    @Test
    public void sortedGetCart(){
        String jsonFilePath = "JSON_FILES"+ File.separator+"Get_All_Carts_SortBy_Desc.json";
        String expectedJson = readJsonFile(jsonFilePath);
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("sort","desc")
                .when()
                .get(APIResources.ADD_CART)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,7);
        int firstId=response.path("id[0]");
        Assert.assertEquals(firstId,7);
        expectedJson=expectedJson.replaceAll("\\r\\n?", "\n");
        String actualJson=response.body().prettyPrint().replaceAll("\\r\\n?", "\n");;
        Assert.assertEquals(actualJson,expectedJson);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
    @Test
    public void sortedGetCartBYLimit(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("limit",5)
                .when()
                .get(APIResources.ADD_CART)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,5);
        int firstId=response.path("id[0]");
        Assert.assertEquals(firstId,1);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;

    }
}
