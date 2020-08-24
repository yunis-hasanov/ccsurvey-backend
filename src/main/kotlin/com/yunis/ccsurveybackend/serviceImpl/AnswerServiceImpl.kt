package com.yunis.ccsurveybackend.serviceImpl

import com.yunis.ccsurveybackend.models.Answer
import com.yunis.ccsurveybackend.models.AnswerExt
import com.yunis.ccsurveybackend.service.AnswerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AnswerServiceImpl : AnswerService {

    @Autowired
    lateinit var answerService: AnswerService;

    override fun getAllByQId(QID: Int): List<AnswerExt> = answerService.getAllByQId(QID);

    override fun getAnswerById(ID: Int): Answer = answerService.getAnswerById(ID);

    override fun addAnswer(QID: Int, NextQID: Int?, Title: String, Text_az: String?, Text_en: String?, Text_ru: String?, Q_Order: Int?, IsEnabled: Int?) {
        try {
            answerService.addAnswer(QID, NextQID, Title?.trim(), Text_az?.trim(), Text_en?.trim(), Text_ru?.trim(), Q_Order, IsEnabled);
        } catch (exc: Exception) {
            //todo throw Custom Exception
        }
    }

    override fun updateAnswer(ID: Int, QID: Int, NextQID: Int?, Title: String, Text_az: String?, Text_en: String?, Text_ru: String?, Q_Order: Int?, IsEnabled: Int?) {
        try {
            answerService.updateAnswer(ID, QID, NextQID, Title?.trim(), Text_az?.trim(), Text_en?.trim(), Text_ru?.trim(), Q_Order, IsEnabled);
        } catch (exc: Exception) {
            //todo throw Custom Exception
        }
    }


    fun updateAnswers(answers: List<Answer>) {
        try {
            for (item in answers) {
                if (item.ID == 0) {
                    answerService.addAnswer(item.QID, item.NextQID, item.Title?.trim(), item.Text_az?.trim(), item.Text_en?.trim(), item.Text_ru?.trim(), item.Q_Order, item.IsEnabled);
                } else {
                    answerService.updateAnswer(item.ID, item.QID, item.NextQID, item.Title?.trim(), item.Text_az?.trim(), item.Text_en?.trim(), item.Text_ru?.trim(), item.Q_Order, item.IsEnabled);
                }

            }
            //
        } catch (exc: Exception) {
            //todo throw Custom Exception
        }
    }

}