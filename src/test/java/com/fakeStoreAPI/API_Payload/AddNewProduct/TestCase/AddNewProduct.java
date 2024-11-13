package com.fakeStoreAPI.API_Payload.AddNewProduct.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.AddProduct;
import com.fakeStoreAPI.Utils.ProductUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class AddNewProduct {
    private int id;
    @Test
    public void addNewProduct() {
        AddProduct productUtils= new ProductUtils().productDetails();
        Response response = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type","application/json")
                .body(productUtils)
        .when()
                .log().all()
                .post(APIResources.ADD_PRODUCT)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        id=response.path("id");
        String titleGet=response.path("title");
        int priceGet=response.path("price");
        String descriptionGet=response.path("description");
        String categoryGet=response.path("category");
        String imageGet=response.path("image");
        Assert.assertEquals(titleGet,productUtils.getTitle());
        Assert.assertEquals(priceGet,productUtils.getPrice());
        Assert.assertEquals(descriptionGet,productUtils.getDescription());
        Assert.assertEquals(categoryGet,productUtils.getCategory());
        Assert.assertTrue(imageGet.contains(".jpg"));
        Assert.assertEquals(id,21);
        long time=response.getTimeIn(TimeUnit.MILLISECONDS);
        assert time<2000;
        System.out.println(id);
    }

    public int getId() {
        return id;
    }

    public static void main(String[] args) {
        AddNewProduct addNewProduct = new AddNewProduct();
        addNewProduct.addNewProduct();
        System.out.println(addNewProduct.getId());
    }
}
