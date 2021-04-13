package com.moneylion.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class EmployeeTests {

    private static String projectRequestBody = "{\"name\": \"Grocery List\"}";
    private static String taskRequestBody = "{\"content\": \"Buy Kombucha\", \"project_id\": 2262466429}";
    private static String updateTaskRequestBody = "{\"due_string\": \"tomorrow\"}";
    private static String bearerToken = "ada93225217defb21d7b33afa0f01b6c3b5d1f61";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
    }

    @Test
    public void createNewProject() {
        Response response = given()
                .headers("Authorization",
                        "Bearer " + bearerToken,
                        "Content-type", "application/json")
                .and()
                .body(projectRequestBody)
                .when()
                .post("/rest/v1/projects")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Grocery List", response.jsonPath().getString("name"));
        }

    @Test
    public void createNewTask() {
        Response response = given()
                .headers("Authorization",
                        "Bearer " + bearerToken,
                        "Content-type", "application/json")
                .and()
                .body(taskRequestBody)
                .when()
                .post("/rest/v1/tasks")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Buy Kombucha", response.jsonPath().getString("content"));
    }

    @Test
    public void updateTaskContent() {
        Response response = given()
                .headers("Authorization",
                        "Bearer " + bearerToken,
                        "Content-type", "application/json")
                .and()
                .body(updateTaskRequestBody)
                .when()
                .post("/rest/v1/tasks/4717237076")
                .then()
                .extract().response();

        Assertions.assertEquals(204, response.statusCode());
    }
}