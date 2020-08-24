package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor
import java.sql.Date

open class Survey @AutomapConstructor constructor(var ID:Int?,
                                                  var CampID:Int,
                                                  var AgentID:Int,
                                                  var CustomerName: String?,
                                                  var CIF:String?,
                                                  var MSISDN:String?,
                                                  var CDATE: Date?){

}