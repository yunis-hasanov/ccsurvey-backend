package com.yunis.ccsurveybackend.controllers

import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("ccsurvey-backend/")
class MainController {

    //@CrossOrigin
    @GetMapping(value = ["/ping"])
    fun ping() :String {

     return   "Pong!"

    }

}
