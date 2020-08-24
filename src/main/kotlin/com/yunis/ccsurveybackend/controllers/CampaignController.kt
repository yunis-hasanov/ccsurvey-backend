package com.yunis.ccsurveybackend.controllers

import com.yunis.ccsurveybackend.models.Campaign
import com.yunis.ccsurveybackend.models.responses.Response
import com.yunis.ccsurveybackend.serviceImpl.CampaignServiceImpl
import com.yunis.ccsurveybackend.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/")
class CampaignController {

    @Autowired
    lateinit var campaignServiceImpl: CampaignServiceImpl

    @GetMapping(value = ["/campaigns"])
    fun getCampaigns():Response{
        var campaigns = campaignServiceImpl.getAll();
        if (campaigns == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,campaigns);
        return campaignResponse;
    }

    @GetMapping(value = ["/campaigns/{Id}"])
    fun getCampaignById(@PathVariable Id: Int):Response{
        var campaign = campaignServiceImpl.getCampById(Id);
        if (campaign == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, campaign);
        return campaignResponse;
    }

    @PostMapping(value = ["/campaigns"])
    @ResponseStatus(HttpStatus.CREATED)
    fun addCampaign(@RequestBody campaign: Campaign):Response{
        if (campaign.CampaignName.isNullOrBlank()) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        campaignServiceImpl.addCampaign(campaign.CampaignName, campaign.IsEnabled);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return campaignResponse;
    }
    @CrossOrigin
    @PutMapping(value = ["/campaigns"])
    @ResponseStatus(HttpStatus.OK)
    fun updateCampaign(@RequestBody campaign: Campaign):Response{
        if (campaign.CampaignName.isNullOrBlank()) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        campaignServiceImpl.updateCampaign(campaign.ID, campaign.CampaignName, campaign.IsEnabled);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return campaignResponse;
    }

}