package com.fakeStoreAPI.Config;

import io.restassured.path.json.JsonPath;

public class JsonGet {
    public static JsonPath json(String resource){
        JsonPath jsonpath=new JsonPath(resource);
        return jsonpath;
    }
}
