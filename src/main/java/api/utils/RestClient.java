package api.utils;

import api.enums.RequestType;
import api.enums.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    public Response getResponse(RequestType requestType, RequestSpecification requestSpec, StatusCode expectedStatusCode) {
        return switch (requestType) {
            case GET -> RestAssured.given(requestSpec)
                    .when().get()
                    .then().statusCode(expectedStatusCode.getCode())
                    .extract().response();
            case POST -> RestAssured.given(requestSpec)
                    .when().post()
                    .then().statusCode(expectedStatusCode.getCode())
                    .extract().response();
            case PATCH -> RestAssured.given(requestSpec)
                    .when().patch()
                    .then().statusCode(expectedStatusCode.getCode())
                    .extract().response();
            case PUT -> RestAssured.given(requestSpec)
                    .when().put()
                    .then().statusCode(expectedStatusCode.getCode())
                    .extract().response();
            case DELETE -> RestAssured.given(requestSpec)
                    .when().delete()
                    .then().statusCode(expectedStatusCode.getCode())
                    .extract().response();
            default -> throw new IllegalArgumentException("Invalid request type: " + requestType);
        };
    }
}
