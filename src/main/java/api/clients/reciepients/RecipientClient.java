package api.clients.reciepients;

import api.endpoints.RecipientsEndpoints;
import api.endpoints.Service;
import api.enums.StatusCode;
import api.utils.RestClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Collections;
import java.util.Map;

import static api.enums.RequestType.GET;


public class RecipientClient {
    private final RestClient restClient = new RestClient();
    
    public RecipientClient(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Map<String,String> defaultHeaders(){
        return Collections.singletonMap("Content-Type","application/json");
    }

    public Response getAllRecipients(){
        RequestSpecification requestSpec = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBasePath(RecipientsEndpoints.GET_RECIPIENTS.getPath())
                .setBaseUri(Service.RECIPIENTS.getUri())
                .build();
        return restClient.getResponse(GET,requestSpec, StatusCode.OK);
    }

    public Response getRecipientId(String recipientId){
        RequestSpecification requestSpec = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBasePath(RecipientsEndpoints.GET_RECIPIENT_INFO.getPath())
                .setBaseUri(Service.RECIPIENTS.getUri())
                .addPathParam("id",recipientId)
                .build();
        return restClient.getResponse(GET,requestSpec,StatusCode.OK);
    }
}
