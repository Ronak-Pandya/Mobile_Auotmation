package pageObject.android;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class API_Testing {

    String baseUrl = "https://reqres.in/api";

    // 200 OK
    @Test
    public void getUser() {

        given()
                .when()
                .get(baseUrl + "/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    // 201 Created
    @Test
    public void createUser() {

        String body = "{ \"name\":\"Ronak\", \"job\":\"QA\" }";

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(baseUrl + "/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    // 204 No Content
    @Test
    public void deleteUser() {

        given()
                .when()
                .delete(baseUrl + "/users/2")
                .then()
                .statusCode(204);
    }

    // 404 Not Found
    @Test
    public void userNotFound() {

        given()
                .when()
                .get(baseUrl + "/users/999")
                .then()
                .statusCode(404);
    }

    // 400 Bad Request example
    @Test
    public void badRequestExample() {

        String body = "{ \"name\":\"\" }";

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(baseUrl + "/users")
                .then()
                .statusCode(400);
    }

}



