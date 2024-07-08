package api.clients.emailtemplates;

import api.clients.reciepients.RecipientClient;
import api.endpoints.EmailTempEndpoints;
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

public class EmailTemplateClient {
    private final RestClient restClient = new RestClient();
    public EmailTemplateClient(){
        RestAssured.baseURI  = Service.EMAIL_TEMPLATE.getUri();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Map<String,String> defaultHeaders(){
        return Collections.singletonMap("Content-Type","application/json");
    }

    public Response getAllEmailTemplates(){
        RequestSpecification requestSpec = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBasePath(EmailTempEndpoints.GET_ALL_EMAIL_TEMPLATES.getPath())
                .setBaseUri(Service.EMAIL_TEMPLATE.getUri())
                .build();
        return restClient.getResponse(GET,requestSpec,StatusCode.OK);
    }

    public Response getEmailTemplate(String templateId){
        RequestSpecification requestSpec = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBasePath(EmailTempEndpoints.GET_EMAIL_TEMPLATE.getPath())
                .setBaseUri(Service.EMAIL_TEMPLATE.getUri())
                .addPathParam("id",templateId)
                .build();
        return restClient.getResponse(GET,requestSpec,StatusCode.OK);
    }
}
