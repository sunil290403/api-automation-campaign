package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CampaignEndpoints {
    CREATE_CAMPAIGN("campaigns"),
    GET_CAMPAIGNS("campaigns/{campaignId}"),
    UPDATE_CAMPAIGNS("campaigns/{campaignId}/name");

    private final String path;
}
