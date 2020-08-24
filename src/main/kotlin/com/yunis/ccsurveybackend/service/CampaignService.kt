package com.yunis.ccsurveybackend.service

import com.yunis.ccsurveybackend.models.Campaign
import org.apache.ibatis.annotations.*

@Mapper
interface CampaignService {

    @Select("SELECT * FROM tbl_campaign")
    fun getAll():List<Campaign>

    @Select("SELECT * FROM tbl_campaign WHERE ID = #{Id}")
    fun getCampById(@Param("Id") Id: Int):Campaign

    @Insert("INSERT INTO tbl_campaign (CampaignName, CDATE, IsEnabled) VALUES (#{CampaignName}, getdate(), #{IsEnabled})")
    fun addCampaign(@Param("CampaignName") CampaignName:String?, @Param("IsEnabled") IsEnabled:Int?)

    @Update("UPDATE tbl_campaign SET CampaignName = #{CampaignName}, IsEnabled = #{IsEnabled} WHERE ID = #{ID}")
    fun updateCampaign(@Param("ID") Id: Int, @Param("CampaignName") CampaignName:String?, @Param("IsEnabled") IsEnabled:Int?)

}