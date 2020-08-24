package com.yunis.ccsurveybackend.service

import com.yunis.ccsurveybackend.models.Question
import org.apache.ibatis.annotations.*

@Mapper
interface QuestionService {
    @Select("SELECT * FROM tbl_question WHERE CampID = #{CampID} ORDER BY Q_Order")
    fun getAllByCampId(@Param("CampID") CampID: Int):List<Question>;

    @Select("SELECT * FROM tbl_question WHERE CampID = #{CampID} AND ID NOT IN (SELECT distinct NextQID FROM tbl_answer WHERE IsEnabled = 1 AND  NextQID IS NOT NULL) ORDER BY Q_Order")
    fun getAllByCampIdForMap(@Param("CampID") CampID: Int):List<Question>;

    @Select("SELECT * FROM tbl_question WHERE ParentAID = #{AID} ORDER BY Q_Order")
    fun getSubQuestionByParentAID(@Param("AID") AID: Int):List<Question>;

    @Select("SELECT * FROM tbl_question WHERE ID = #{NextQID} ORDER BY Q_Order")
    fun getSubQuestionByNextQID(@Param("NextQID") NextQID: Int):List<Question>;

    @Select("SELECT * FROM tbl_question WHERE ID = #{ID}")
    fun getQuestionById(@Param("ID") ID: Int):Question;

    @Insert("INSERT INTO tbl_question ( CampID, ParentQID, ParentAID, Title, Text_az, Text_en, Text_ru, Q_Order, CDATE, IsEnabled, Note) VALUES (#{CampID},#{ParentQID},#{ParentAID},#{Title},#{Text_az},#{Text_en},#{Text_ru},#{Q_Order},getdate(),#{IsEnabled},#{Note})")
    fun addQuestion(@Param("CampID") CampID: Int,@Param("ParentQID") ParentQID: Int?,@Param("ParentAID") ParentAID: Int?,@Param("Title") Title: String,@Param("Text_az") Text_az: String?,@Param("Text_en") Text_en: String?,@Param("Text_ru") Text_ru: String?,@Param("Q_Order") Q_Order: Int?,@Param("IsEnabled") IsEnabled: Int?, @Param("Note") Note :String?);

    @Update("UPDATE tbl_question SET CampID = #{CampID}, ParentQID = #{ParentQID}, ParentAID = #{ParentAID}, Title = #{Title}, Text_az = #{Text_az}, Text_en = #{Text_en}, Text_ru = #{Text_ru}, Q_Order = #{Q_Order}, IsEnabled = #{IsEnabled}, Note = #{Note} WHERE ID= #{ID}")
    fun updateQuetion(@Param("ID") ID: Int, @Param("CampID") CampID: Int,@Param("ParentQID") ParentQID: Int?,@Param("ParentAID") ParentAID: Int?,@Param("Title") Title: String,@Param("Text_az") Text_az: String?,@Param("Text_en") Text_en: String?,@Param("Text_ru") Text_ru: String?,@Param("Q_Order") Q_Order: Int?,@Param("IsEnabled") IsEnabled: Int?, @Param("Note") Note :String?);
}