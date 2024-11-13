package com.fakeStoreAPI.API_Payload.Cart.TestCase;


import com.fakeStoreAPI.Config.APIResources;
import com.fakeStoreAPI.POJO_Class.AddCart;
import com.fakeStoreAPI.Utils.CartUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewCart {
    int id;
    @Test
    public void addNewUser() {
        AddCart addCart = new CartUtils().addCart();
        Response cartResponse = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type", "application/json")
                .body(addCart)
        .when()
                .log().all()
                .post(APIResources.ADD_CART)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        id=cartResponse.path("id");
        Assert.assertEquals(id,11);
    }
    @Test
    public void updateNewUser() {
        AddCart addCart = new CartUtils().addCart();

        Response cartUpdateResponse = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type", "application/json")
                .body(addCart)
                .when()
                .log().all()
                .put(APIResources.ADD_CART+"/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        id=cartUpdateResponse.path("id");
        Assert.assertEquals(id,11);
    }
    @Test
    public void deleteNewProduct() {
        Response response = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type", "application/json")
        .when()
                .log().all()
        .delete(APIResources.ADD_CART+"/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String body=response.body().asString();
        Assert.assertTrue(body.contains("null"));
    }
}
