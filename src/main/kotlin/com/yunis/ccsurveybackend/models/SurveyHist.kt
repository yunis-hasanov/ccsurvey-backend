package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor

open class SurveyHist @AutomapConstructor constructor(var QText_az:String?,
                                                      var QText_en:String?,
                                                      var QText_ru:String?,
                                                      var AText_az:String?,
                                                      var AText_en:String?,
                                                      var AText_ru:String?,
                                                      var Answer:String?,
                                                      var Note:String?)