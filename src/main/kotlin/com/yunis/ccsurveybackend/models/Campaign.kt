package com.yunis.ccsurveybackend.models

import org.apache.ibatis.annotations.AutomapConstructor
import java.sql.Date


class Campaign @AutomapConstructor constructor(var ID: Int,
                                               var CampaignName: String?,
                                               var CDATE: Date?,
                                               var IsEnabled: Int?) {


}

