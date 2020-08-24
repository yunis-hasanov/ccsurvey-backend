package com.yunis.ccsurveybackend.serviceImpl
import com.yunis.ccsurveybackend.models.*
import com.yunis.ccsurveybackend.service.AnswerService
import com.yunis.ccsurveybackend.service.QuestionService
import com.yunis.ccsurveybackend.service.SurveyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class SurveyServiceImpl :SurveyService{

    @Autowired
    lateinit var surveyService: SurveyService;

    @Autowired
    lateinit var answerService: AnswerService;

    @Autowired
    lateinit var questionService: QuestionService;

    override fun getCampaignList(): List<Campaign>  = surveyService.getCampaignList();

    override fun submitSurveyAnswer(SID: Int, QID: Int, AID: Int, Answer: String?) = surveyService.submitSurveyAnswer(SID, QID, AID, Answer);

    //override fun submitCampaign(CampID: Int?, AgentID: Int?, CustomerName: String?, CIF: String?, MSISDN: String?) = surveyService.submitCampaign(CampID,AgentID,CustomerName, CIF, MSISDN);

    override fun getSurveyBySID(SID: Int) = surveyService.getSurveyBySID(SID);

    override fun submitCampaign(survey: Survey){
        surveyService.submitCampaign(survey)
    }

    override fun getfirstQuestionByCampID(CampID: Int): Question = surveyService.getfirstQuestionByCampID(CampID);

    override fun getnextQuestion(CampID: Int, QID: Int): Question = surveyService.getnextQuestion(CampID, QID);


 fun submitAndGetNextQuestion(surveyAnswer: SurveyAnswer, CampID:Int) : QuestionAnswer{
    var questionAnswer: QuestionAnswer;
    if (surveyAnswer.QID == null && surveyAnswer.AID == null) {
        //Get first question
        var question = surveyService.getfirstQuestionByCampID(CampID);
        var answer = answerService.getAllByQId(question.ID?:0);
        questionAnswer = QuestionAnswer(question,answer);
    } else {
        surveyService.submitSurveyAnswer(surveyAnswer.SID?:0, surveyAnswer.QID?:0, surveyAnswer.AID?:0, surveyAnswer.Answer)
        var answerDetail = answerService.getAnswerById(surveyAnswer.AID?:0);
        if (answerDetail?.NextQID != null){
            // Get next question relates answer
            var question = questionService.getQuestionById(answerDetail.NextQID?:0);
            var answer = answerService.getAllByQId(answerDetail.NextQID?:0);
            questionAnswer = QuestionAnswer(question,answer);
        } else{
            // get next question
            var question = surveyService.getnextQuestion(CampID,surveyAnswer.QID?:0);
            if (question != null) {
                var answer = answerService.getAllByQId(question.ID ?: 0);
                questionAnswer = QuestionAnswer(question, answer);
            }else{
                questionAnswer = QuestionAnswer(null, null);
            }
        }
    }
     return questionAnswer;
}

}