package com.moneylion.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class EmployeeTests {

    private static String requestBody = "{\"name\":\"Patrick\",\"salary\":\"123\",\"age\":\"23\"}";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/api/v1/create")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Patrick", response.jsonPath().getString("name"));
        Assertions.assertEquals("123", response.jsonPath().getString("salary"));
        Assertions.assertEquals("23", response.jsonPath().getString("age"));
        }

    @Test
    public void getRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/employees")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("11", response.jsonPath().getString("id"));
        Assertions.assertEquals("Jena Gaines", response.jsonPath().getString("name"));
    }
    }