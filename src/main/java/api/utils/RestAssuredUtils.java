package api.utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import report.ExtentReportManager;

import java.util.Map;

public class RestAssuredUtils {

    public static Response sendRequest(Method httpMethod, RequestSpecification requestSpec, int expectedStatusCode) {
        return sendRequest(httpMethod, requestSpec, expectedStatusCode, null);
    }

    public static Response sendRequest(Method httpMethod, RequestSpecification requestSpec, int expectedStatusCode, Map<String, Object> expectedResponseBody) {
        Response response = null;
        try {
            switch (httpMethod) {
                case GET:
                    response = requestSpec.get();
                    break;
                case POST:
                    response = requestSpec.post();
                    break;
                case PUT:
                    response = requestSpec.put();
                    break;
                case DELETE:
                    response = requestSpec.delete();
                    break;
                case PATCH:
                    response = requestSpec.patch();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
            }

            int actualStatusCode = response.getStatusCode();
            if (actualStatusCode != expectedStatusCode) {
                throw new RuntimeException("Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
            }

            if (expectedResponseBody != null) {
                Map<String, Object> actualResponseBody = response.jsonPath().getMap("");
                if (!actualResponseBody.equals(expectedResponseBody)) {
                    throw new RuntimeException("Expected response body: " + expectedResponseBody + ", but got: " + actualResponseBody);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sending request: " + e.getMessage(), e);
        }
        return response;
    }

    public static RequestSpecification buildRequestSpec(String baseUri, ContentType contentType, Map<String, Object> headers, Map<String, Object> queryParams, Object requestBody) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.baseUri(baseUri);
        requestSpec.contentType(contentType);

        if (headers != null) {
            requestSpec.headers(headers);
        }

        if (queryParams != null) {
            requestSpec.queryParams(queryParams);
        }

        if (requestBody != null) {
            requestSpec.body(requestBody);
        }

        return requestSpec;
    }


    private static void printLogReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryDetail = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is " + queryDetail.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is " + queryDetail.getMethod());
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response status is " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response body is ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

}
