package com.fakeStoreAPI.API_Payload.AddNewProduct.TestCase;

import com.fakeStoreAPI.Config.APIResources;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteNewProduct {
    @Test
    public void deleteNewProduct() {
        AddNewProduct addNewProduct = new AddNewProduct();
        addNewProduct.addNewProduct();
        int id =addNewProduct.getId();
        Response response = given()
                .baseUri(APIResources.BASE_URL)
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .delete(APIResources.ADD_PRODUCT+"/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        String body=response.body().asString();
        Assert.assertTrue(body.contains("null"));
    }
}
