package com.mircoservices.study.controller

import com.mircoservices.study.Interface.CustomerService
import com.mircoservices.study.data.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    @RequestMapping(value = "/customer/{id}", method = arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable("id") id : Int) = customerService.getCustomer(id)

    @RequestMapping(value = "/customer/", method = arrayOf(RequestMethod.POST))
    fun postCustomer(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }

    @RequestMapping(value ="/customer/{id}", method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable("id") id : Int) {
        customerService.deleteCustomer(id)
    }

    @RequestMapping(value = "/customer/{id}", method = arrayOf(RequestMethod.PUT))
    fun putCustomer(@PathVariable("id")id: Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }

    @RequestMapping(value = "/customers", method = arrayOf(RequestMethod.GET))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.serachCustomers(nameFilter)


}
