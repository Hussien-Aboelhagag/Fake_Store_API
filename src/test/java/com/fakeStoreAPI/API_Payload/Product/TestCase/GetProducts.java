package com.fakeStoreAPI.API_Payload.Product.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.Config.Databases;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class GetProducts {
    @Test
    public void getAllProducts(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
                .when()
                .get(APIResources.ADD_PRODUCT)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        int size=response.path("id.size()");
        Assert.assertEquals(size,20);
        for (int i=0;i<size;i++){
            String titleGet=response.path("title["+i+"]");
            Assert.assertEquals(titleGet, Databases.PRODUCT_TITLE_DATABASE[i]);
            String categoryGet=response.path("category["+i+"]");
            Assert.assertEquals(categoryGet, Databases.PRODUCT_CATEGORY_DATABASE[i]);
            String imageGet=response.path("image["+i+"]");
            Assert.assertTrue(imageGet.contains(".jpg"));
        }
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<3000;
    }
    @Test
    public void getSingleProduct(){
        Response response=given()
                .baseUri(APIResources.BASE_URL)
        .when()
                .get(APIResources.ADD_PRODUCT+APIResources.USER_ID)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String titleGet=response.path("title");
        Assert.assertEquals(titleGet, Databases.PRODUCT_TITLE_DATABASE[0]);
        String categoryGet=response.path("category");
        Assert.assertEquals(categoryGet, Databases.PRODUCT_CATEGORY_DATABASE[0]);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<1000;
    }
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
