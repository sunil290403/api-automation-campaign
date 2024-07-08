package api.clients.campaigns;

import api.endpoints.CampaignEndpoints;
import api.endpoints.Service;
import api.enums.StatusCode;
import api.pojos.campaigns.CreateCampaignRequest;
import api.utils.RestClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.util.Collections;
import java.util.Map;

import static api.enums.RequestType.*;
import static io.restassured.RestAssured.*;

public class CampaignClient {
    private final RestClient restClient = new RestClient();
    
    public CampaignClient(){
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Map<String,String> defaultHeaders(){
        return Collections.singletonMap("Content-Type","application/json");
    }

    public Response createCampaign(CreateCampaignRequest campaignRequest , StatusCode statusCode){
        RequestSpecification requestSpecification = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBaseUri(Service.CAMPAIGN.getUri())
                .setBasePath(CampaignEndpoints.CREATE_CAMPAIGN.getPath()).setBody(campaignRequest).build();
        return restClient.getResponse(POST,requestSpecification,statusCode);
    }

    public Response updateCampaign(String campaignId , String campaignName , StatusCode statusCode){
        Map<String,String> request = Collections.singletonMap("campaignName",campaignName);
        RequestSpecification requestSpecification = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .setBasePath(CampaignEndpoints.UPDATE_CAMPAIGNS.getPath())
                .setBaseUri(Service.CAMPAIGN.getUri())
                .addPathParam("campaignId", campaignId)
                .setBody(request).build();
        return restClient.getResponse(PATCH,requestSpecification,statusCode);
    }

    public Response getCampaign(String campaignId , StatusCode statusCode){
        RequestSpecification requestSpec = new RequestSpecBuilder().addHeaders(defaultHeaders())
                .addPathParam("campaignId",campaignId)
                .setBaseUri(Service.CAMPAIGN.getUri())
                .setBasePath(CampaignEndpoints.GET_CAMPAIGNS.getPath())
                .build();
        return restClient.getResponse(GET,requestSpec,statusCode);
    }
}
