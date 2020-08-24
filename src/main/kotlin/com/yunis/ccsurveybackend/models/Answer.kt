package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor
import java.sql.Date

open class Answer @AutomapConstructor constructor(var ID: Int,
                                                  var QID: Int,
                                                  var NextQID: Int?,
                                                  var Title: String,
                                                  var Text_az: String?,
                                                  var Text_en: String?,
                                                  var Text_ru: String?,
                                                  var Q_Order: Int?,
                                                  var CDATE: Date?,
                                                  var IsEnabled: Int?) {

}