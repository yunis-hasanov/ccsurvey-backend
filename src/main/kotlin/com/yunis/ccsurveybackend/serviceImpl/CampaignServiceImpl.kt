package com.yunis.ccsurveybackend.serviceImpl

import com.yunis.ccsurveybackend.models.Campaign
import com.yunis.ccsurveybackend.service.CampaignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CampaignServiceImpl :CampaignService{

    @Autowired
    lateinit var campaignService:CampaignService

    override fun getAll():List<Campaign> = campaignService.getAll();

    override fun getCampById(Id: Int):Campaign = campaignService.getCampById(Id);



    override fun addCampaign(CampaignName:String?, IsEnabled:Int?){
        try {
            campaignService.addCampaign(CampaignName?.trim(), IsEnabled);
        }
        catch (exc:Exception){
            //todo throw Custom Exception
        }
    }

    override fun updateCampaign(Id: Int, CampaignName:String?, IsEnabled:Int?){
        try {
            campaignService.updateCampaign(Id,CampaignName?.trim(), IsEnabled);
        }
        catch (exc:Exception){
            //todo throw Custom Exception
        }
    }
}