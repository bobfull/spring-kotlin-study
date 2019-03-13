package com.mircoservices.study

import com.mircoservices.study.data.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class StudyApplication {
    companion object {
        val initialCustomers = arrayOf(Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice"))
    }

    @Bean
    fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))



    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}'=='simple'}")
    fun exampleService() : ServiceInterface = ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}'=='advance'}")
    fun advanceService() : ServiceInterface = AdvanceService()
}

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}
