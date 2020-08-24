package com.yunis.ccsurveybackend.controllers

import com.yunis.ccsurveybackend.models.Survey
import com.yunis.ccsurveybackend.models.SurveyAnswer
import com.yunis.ccsurveybackend.models.responses.Response
import com.yunis.ccsurveybackend.serviceImpl.SurveyServiceImpl
import com.yunis.ccsurveybackend.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("survey/")
class SurveyController {

    @Autowired
    lateinit var surveyServiceImpl: SurveyServiceImpl;

    @GetMapping(value = ["/campaigns"])
    fun getCampaigns(): Response {
        var campaigns = surveyServiceImpl.getCampaignList();
        if (campaigns == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, campaigns);
        return campaignResponse;
    }


    @PostMapping(value = ["/campaigns"])
    @ResponseStatus(HttpStatus.CREATED)
    fun submitCampaign(@RequestBody survey: Survey): Response {
        var survey = Survey(null, survey.CampID, survey.AgentID, survey.CustomerName, survey.CIF, survey.MSISDN, null)
        surveyServiceImpl.submitCampaign(survey);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, survey);
        return campaignResponse;
    }


    @PostMapping(value = ["/campaign/{CampID}/questions"])
    @ResponseStatus(HttpStatus.CREATED)
    fun submitAndGetNextQuestion(@RequestBody surveyAnswer: SurveyAnswer, @PathVariable CampID: Int): Response {
        if (CampID == 0 || surveyAnswer.SID == null) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        var questionAnswer = surveyServiceImpl.submitAndGetNextQuestion(surveyAnswer, CampID);
        if (questionAnswer.Qstn == null && questionAnswer.Answ == null){
            return Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        }
        var response = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, questionAnswer);
        return response;

    }

    @GetMapping(value = ["/{SID}"])
    fun getSurveyBySID(@PathVariable SID: Int): Response {
        var surveyHist = surveyServiceImpl.getSurveyBySID(SID);
        if (surveyHist == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var campaignResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, surveyHist);
        return campaignResponse;
    }


}