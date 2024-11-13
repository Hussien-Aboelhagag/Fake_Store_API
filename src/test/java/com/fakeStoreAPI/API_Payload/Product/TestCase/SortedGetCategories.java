package com.fakeStoreAPI.API_Payload.Product.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class SortedGetCategories {
    @Test
    public void sortedGetAllCategories(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.PRODUCT_CATEGORIES)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String responseBody = response.body().asString();
        Assert.assertTrue(responseBody.contains("men's clothing"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
    @Test
    public void sortedGetCategoriesByName(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.PRODUCT_CATEGORY)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String responseBody = response.body().asString();
        Assert.assertFalse(responseBody.contains("men's clothing"));
        int idNum=response.path("id.size()");
        Assert.assertEquals(idNum,6);
        String firstTitle=response.path("title[0]");
        Assert.assertTrue(firstTitle.contains("External Hard Drive"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
    @Test
    public void sortedGetCategoriesByNameAndDesc(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("sort","desc")
        .when()
                .get(APIResources.PRODUCT_CATEGORY)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String responseBody = response.body().asString();
        Assert.assertFalse(responseBody.contains("men's clothing"));
        int idNum=response.path("id.size()");
        Assert.assertEquals(idNum,6);
        String firstTitle=response.path("title[0]");
        Assert.assertTrue(firstTitle.contains("Samsung"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
}
