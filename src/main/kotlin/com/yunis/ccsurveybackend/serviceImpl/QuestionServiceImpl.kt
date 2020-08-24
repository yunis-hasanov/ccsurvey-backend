package com.yunis.ccsurveybackend.serviceImpl

import com.yunis.ccsurveybackend.models.CampaignMap
import com.yunis.ccsurveybackend.models.Question
import com.yunis.ccsurveybackend.service.AnswerService
import com.yunis.ccsurveybackend.service.QuestionService
import org.apache.ibatis.annotations.Param
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class QuestionServiceImpl : QuestionService {

    @Autowired
    lateinit var questionService: QuestionService;
    @Autowired
    lateinit var answerService: AnswerService;

    override fun getAllByCampId(CampID: Int): List<Question> = questionService.getAllByCampId(CampID);

    override fun getAllByCampIdForMap(CampID: Int): List<Question> = questionService.getAllByCampIdForMap(CampID);

    override fun getSubQuestionByParentAID(AID: Int):List<Question> = questionService.getSubQuestionByParentAID(AID);

    override fun getSubQuestionByNextQID(NextQID: Int): List<Question> = questionService.getSubQuestionByNextQID(NextQID);

    fun getCampaignMap(CampID: Int): CampaignMap {
        var campaignMap = CampaignMap();
        campaignMap.name = "Campaign Root";
        var campaignMapChild = mutableListOf<CampaignMap>();
        var questionList = questionService.getAllByCampIdForMap(CampID);
        for (qitem in questionList) {
            var campaignMapItem = CampaignMap();
            campaignMapItem.name = qitem.Title;
            campaignMapItem.children = getAnswersForMap(qitem.ID);
            campaignMapChild.add(campaignMapItem);
        }
        campaignMap.children = campaignMapChild;
        return campaignMap;
    }

    fun getAnswersForMap(QID:Int):Any?{
        var campaignMapList = mutableListOf<CampaignMap>();
        var answerList = answerService.getAllByQId(QID);
        if (answerList.size > 0) {
            for (aitem in answerList) {
                var campaignMapItem = CampaignMap();
                campaignMapItem.name = aitem.Title;
                if (aitem.NextQID != null) {
                campaignMapItem.children = getSubQuestionForMap(aitem.NextQID?:0);
                }
                campaignMapList.add(campaignMapItem);
            }
        }
        return campaignMapList;
    }

    fun getSubQuestionForMap(NextQID:Int):Any?{
        var campaignMapList = mutableListOf<CampaignMap>();
        var subQuestionList = questionService.getSubQuestionByNextQID(NextQID);
        if (subQuestionList.size > 0) {
            for (qitem in subQuestionList) {
                var campaignMapItem = CampaignMap();
                campaignMapItem.name = qitem.Title;
                campaignMapItem.children = getAnswersForMap(qitem.ID);
                campaignMapList.add(campaignMapItem);
            }
        }
        return campaignMapList;
    }










    override fun getQuestionById(ID: Int): Question = questionService.getQuestionById(ID);

    override fun addQuestion(CampID: Int, ParentQID: Int?, ParentAID: Int?, Title: String, Text_az: String?, Text_en: String?, Text_ru: String?, Q_Order: Int?, IsEnabled: Int?, Note : String?) {
        try {
            questionService.addQuestion(CampID, ParentQID, ParentAID, Title, Text_az, Text_en, Text_ru, Q_Order, IsEnabled, Note);
        } catch (exc: Exception) {
            //todo throw Custom Exception
        }
    }

    override fun updateQuetion(ID: Int, CampID: Int, ParentQID: Int?, ParentAID: Int?, Title: String, Text_az: String?, Text_en: String?, Text_ru: String?, Q_Order: Int?, IsEnabled: Int?, Note: String?) {
        try {
            questionService.updateQuetion(ID, CampID, ParentQID, ParentAID, Title, Text_az, Text_en, Text_ru, Q_Order, IsEnabled, Note);
        } catch (exc: Exception) {
            //todo throw Custom Exception
        }
    }

}