package com.fakeStoreAPI.API_Payload.Product.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class SortedGetProducts {
    @Test
    public void sortedGetProduct(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("sort","desc")
        .when()
                .get(APIResources.ADD_PRODUCT)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,20);
        int firstId=response.path("id[0]");
        Assert.assertEquals(firstId,20);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
    @Test
    public void sortedGetProductBYLimit(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .queryParam("limit",5)
        .when()
                .get(APIResources.ADD_PRODUCT)
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
