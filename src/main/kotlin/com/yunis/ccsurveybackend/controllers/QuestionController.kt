package com.yunis.ccsurveybackend.controllers

import com.yunis.ccsurveybackend.models.Question
import com.yunis.ccsurveybackend.models.responses.Response
import com.yunis.ccsurveybackend.serviceImpl.QuestionServiceImpl
import com.yunis.ccsurveybackend.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/")
class QuestionController {

    @Autowired
    lateinit var questionServiceImpl: QuestionServiceImpl;


    @GetMapping(value = ["/campaigns/{CampId}/questions"])
    fun getAllByCampId(@PathVariable CampId: Int):Response{
        var questions = questionServiceImpl.getAllByCampId(CampId);
        if (questions == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var questionResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, questions);
        return questionResponse;
    }
    @GetMapping(value = ["/campaigns/{CampId}/map"])
    fun getCampaignByIdMap(@PathVariable CampId: Int):Response{
        var questions = questionServiceImpl.getCampaignMap(CampId);
        if (questions == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var questionResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, questions);
        return questionResponse;
    }

    @GetMapping(value = ["/campaigns/{CampId}/questions/{Id}"])
    fun getQuestionById(@PathVariable Id: Int):Response{
        var question = questionServiceImpl.getQuestionById(Id);
        if (question == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var questionResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, question);
        return questionResponse;
    }


    @PostMapping(value = ["/campaigns/{CampId}/questions"])
    @ResponseStatus(HttpStatus.CREATED)
    fun addQuestion(@RequestBody question: Question):Response{
        questionServiceImpl.addQuestion(question.CampID,question.ParentQID,question.ParentAID,
                                       question.Title,question.Text_az,question.Text_en,question.Text_ru,
                                       question.Q_Order,question.IsEnabled, question.Note);

        var questionResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return questionResponse;

    }

    @PutMapping(value = ["/campaigns/{CampId}/questions"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateQuestion(@RequestBody question: Question):Response{
        questionServiceImpl.updateQuetion(question.ID, question.CampID,question.ParentQID,question.ParentAID,
                question.Title,question.Text_az,question.Text_en,question.Text_ru,
                question.Q_Order,question.IsEnabled, question.Note);

        var questionResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return questionResponse;

    }

}