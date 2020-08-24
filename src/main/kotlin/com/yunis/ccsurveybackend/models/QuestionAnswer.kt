package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor

open class QuestionAnswer @AutomapConstructor constructor(var Qstn : Question?,
                                                          var Answ :  List<AnswerExt>?){}