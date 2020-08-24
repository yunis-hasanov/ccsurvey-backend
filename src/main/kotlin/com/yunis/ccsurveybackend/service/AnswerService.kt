package com.yunis.ccsurveybackend.service

import com.yunis.ccsurveybackend.models.Answer
import com.yunis.ccsurveybackend.models.AnswerExt
import org.apache.ibatis.annotations.*

@Mapper
interface AnswerService {

//    @Select("SELECT * FROM tbl_answer WHERE QID = #{QID} ORDER BY Q_Order")
    @Select("SELECT answ.ID, answ.QID, answ.NextQID, answ.Title, answ.Text_az, answ.Text_en, answ.Text_ru, answ.Q_Order, answ.CDATE, answ.IsEnabled, qstn.Title as NextQtext FROM tbl_answer as answ LEFT JOIN tbl_question as qstn ON answ.NextQID = qstn.ID  WHERE answ.QID = #{QID} ORDER BY Q_Order")
    fun getAllByQId(@Param("QID") QID: Int): List<AnswerExt>;

    @Select("SELECT * FROM tbl_answer WHERE ID = #{ID}")
    fun getAnswerById(@Param("ID") ID: Int): Answer;

    @Insert("INSERT INTO tbl_answer ( QID, NextQID, Title, Text_az, Text_en, Text_ru, Q_Order, CDATE, IsEnabled) VALUES (#{QID}, #{NextQID}, #{Title},#{Text_az},#{Text_en},#{Text_ru},#{Q_Order},getdate(),#{IsEnabled})")
    fun addAnswer(@Param("QID") QID: Int, @Param("NextQID") NextQID: Int?, @Param("Title") Title: String, @Param("Text_az") Text_az: String?, @Param("Text_en") Text_en: String?, @Param("Text_ru") Text_ru: String?, @Param("Q_Order") Q_Order: Int?, @Param("IsEnabled") IsEnabled: Int?);

    @Update("UPDATE tbl_answer SET QID = #{QID}, NextQID = #{NextQID}, Title = #{Title}, Text_az = #{Text_az}, Text_en = #{Text_en}, Text_ru = #{Text_ru}, Q_Order = #{Q_Order}, IsEnabled = #{IsEnabled} WHERE ID= #{ID}")
    fun updateAnswer(@Param("ID") ID: Int, @Param("QID") QID: Int, @Param("NextQID") NextQID: Int?, @Param("Title") Title: String, @Param("Text_az") Text_az: String?, @Param("Text_en") Text_en: String?, @Param("Text_ru") Text_ru: String?, @Param("Q_Order") Q_Order: Int?, @Param("IsEnabled") IsEnabled: Int?);
}