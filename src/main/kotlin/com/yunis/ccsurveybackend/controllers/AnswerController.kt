package com.yunis.ccsurveybackend.controllers

import com.yunis.ccsurveybackend.models.Answer
import com.yunis.ccsurveybackend.models.responses.Response
import com.yunis.ccsurveybackend.serviceImpl.AnswerServiceImpl
import com.yunis.ccsurveybackend.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/")
class AnswerController {

    @Autowired
    lateinit var answerServiceImpl: AnswerServiceImpl;

    @GetMapping(value = ["/questions/{QID}/answers"])
    fun getAllByQId(@PathVariable QID: Int): Response {
        var answers = answerServiceImpl.getAllByQId(QID);
        if (answers == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var answerResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, answers);
        return answerResponse;
    }

    @GetMapping(value = ["/questions/{QId}/answers/{Id}"])
    fun getAnswerById(@PathVariable Id: Int): Response {
        var answer = answerServiceImpl.getAnswerById(Id);
        if (answer == null) return Response(Constants.NO_DATA_FOUND_CODE, Constants.NO_DATA_FOUND_MSG, null);
        var answerResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, answer);
        return answerResponse;
    }

    @PostMapping(value = ["/questions/{QID}/answer"])
    @ResponseStatus(HttpStatus.CREATED)
    fun addAnswer(@RequestBody answer: Answer): Response {
        if (answer.Title.isNullOrBlank()) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        answerServiceImpl.addAnswer(answer.QID, answer.NextQID, answer.Title, answer.Text_az, answer.Text_en, answer.Text_ru,
                answer.Q_Order, answer.IsEnabled);
        var answerResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return answerResponse;

    }

    @PutMapping(value = ["/questions/{QID}/answer"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateQuestion(@RequestBody answer: Answer): Response {
        if (answer.Title.isNullOrBlank()) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        answerServiceImpl.updateAnswer(answer.ID, answer.QID, answer.NextQID,
                answer.Title, answer.Text_az, answer.Text_en, answer.Text_ru,
                answer.Q_Order, answer.IsEnabled);
        var answerResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return answerResponse;

    }

    @PutMapping(value = ["/questions/{QID}/answers"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateQuestions(@RequestBody answers: List<Answer>): Response {
        for (item in answers){
            if (item.Title.isNullOrBlank()) return Response(Constants.INVALID_ARGUMENT_CODE, Constants.INVALID_ARGUMENT_MSG, null);
        }
        answerServiceImpl.updateAnswers(answers);
        var answerResponse = Response(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, null);
        return answerResponse;

    }

}