package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor

open class SurveyAnswer @AutomapConstructor constructor(var SID:Int?,
                                                        var QID:Int?,
                                                        var AID: Int?,
                                                        var Answer:String?){

}