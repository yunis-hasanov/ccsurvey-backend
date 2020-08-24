package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor
import java.sql.Date

class Question @AutomapConstructor constructor(var ID: Int,
                                               var CampID: Int,
                                               var ParentQID: Int?,
                                               var ParentAID: Int?,
                                               var Title: String,
                                               var Text_az: String?,
                                               var Text_en: String?,
                                               var Text_ru: String?,
                                               var Q_Order: Int?,
                                               var CDATE: Date?,
                                               var IsEnabled: Int?,
                                               var Note: String?) {

}