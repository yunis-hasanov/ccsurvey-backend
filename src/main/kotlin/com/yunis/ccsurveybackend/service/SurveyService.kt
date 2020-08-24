package com.yunis.ccsurveybackend.service

import com.yunis.ccsurveybackend.models.Campaign
import com.yunis.ccsurveybackend.models.Question
import com.yunis.ccsurveybackend.models.Survey
import com.yunis.ccsurveybackend.models.SurveyHist
import org.apache.ibatis.annotations.*

@Mapper
interface SurveyService {

    @Select("SELECT * FROM tbl_campaign WHERE IsEnabled = 1")
    fun getCampaignList():List<Campaign>;

    @Select("SELECT q.Text_az as QText_az, q.Text_en as QText_en, q.Text_ru as QText_ru, a.Text_az as AText_az, a.Text_en as AText_en, a.Text_ru as AText_ru, sa.Answer, q.Note FROM tbl_surveyAnswers sa JOIN tbl_question q ON q.ID = SA.QID JOIN tbl_answer a ON a.ID = sa.AID WHERE sa.SID = #{SID}")
    fun getSurveyBySID(@Param("SID") SID:Int):List<SurveyHist>;

    @Insert("INSERT INTO tbl_survey (CampID, AgentID, CustomerName, CIF, MSISDN) VALUES (#{CampID}, #{AgentID}, #{CustomerName}, #{CIF}, #{MSISDN});")
    @Options(useGeneratedKeys=true,  keyProperty = "ID", keyColumn="ID")
    fun submitCampaign(survey: Survey);


    @Select("SELECT TOP 1 * FROM tbl_question WHERE CampID = #{CampID} AND IsEnabled = 1 ORDER by Q_Order")
    fun getfirstQuestionByCampID(@Param("CampID") CampID:Int):Question;

    @Select("SELECT TOP 1 * FROM tbl_question WHERE CampID = #{CampID} AND IsEnabled = 1 AND ParentAID IS NULL AND Q_Order > (SELECT Q_Order FROM tbl_question WHERE ID = #{QID}) ORDER by Q_Order")
    fun getnextQuestion(@Param("CampID") CampID:Int, @Param("QID") QID:Int):Question;

    @Insert("INSERT INTO tbl_surveyAnswers (SID, QID, AID, Answer) VALUES (#{SID}, #{QID}, #{AID}, #{Answer})")
    fun submitSurveyAnswer(@Param("SID") SID:Int, @Param("QID") QID:Int, @Param("AID") AID:Int, @Param("Answer") Answer:String?);
}