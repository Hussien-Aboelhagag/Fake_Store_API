package com.fakeStoreAPI.API_Payload.AddNewProduct.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.AddProduct;
import com.fakeStoreAPI.Utils.ProductUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class UpdateNewProduct {
    AddNewProduct addNewProduct = new AddNewProduct();
    int idGet;

    @Test
    public void updateNewProduct() {
        addNewProduct.addNewProduct();
        int id =addNewProduct.getId();
        AddProduct productUtils= new ProductUtils().productDetails();
        Response response = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(productUtils)
        .when()
                .log().all()
        .put(APIResources.ADD_PRODUCT+"/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        idGet=response.path("id");
        String titleGet=response.path("title");
        int priceGet=response.path("price");
        String descriptionGet=response.path("description");
        String categoryGet=response.path("category");
        String imageGet=response.path("image");
        Assert.assertEquals(idGet,21);
        Assert.assertEquals(titleGet,productUtils.getTitle());
        Assert.assertEquals(priceGet,productUtils.getPrice());
        Assert.assertEquals(descriptionGet,productUtils.getDescription());
        Assert.assertEquals(categoryGet,productUtils.getCategory());
        Assert.assertTrue(imageGet.contains(".jpg"));
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<3000;
        System.out.println(time);
    }

}
