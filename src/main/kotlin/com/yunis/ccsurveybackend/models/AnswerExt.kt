package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor
import java.sql.Date

open class AnswerExt : Answer {
   var NextQtext:String? = null;
    @AutomapConstructor
   constructor(ID: Int,
               QID: Int,
               NextQID: Int?,
               Title: String,
               Text_az: String?,
               Text_en: String?,
               Text_ru: String?,
               Q_Order: Int?,
               CDATE: Date?,
               IsEnabled: Int?,
               NextQtext: String?) : super(ID, QID, NextQID, Title, Text_az, Text_en, Text_ru, Q_Order, CDATE, IsEnabled) {


    }

}