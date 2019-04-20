package com.example.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloController {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun hello(): String {
        return "Hello!"
    }
}
