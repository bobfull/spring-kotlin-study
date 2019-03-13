package com.mircoservices.study.controller;

import com.mircoservices.study.data.ComplexObject
import com.mircoservices.study.data.SimpleObject
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class JsonExampleController {
    @GetMapping("/json")
    fun getJson() = ComplexObject(object1 = SimpleObject("more", "complex"))
}
