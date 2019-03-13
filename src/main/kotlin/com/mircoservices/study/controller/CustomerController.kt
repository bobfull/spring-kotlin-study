package com.mircoservices.study.controller

import com.mircoservices.study.data.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = "/customer/{id}", method = arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable("id") id : Int) = customers[id]

    @RequestMapping(value = "/customer/", method = arrayOf(RequestMethod.POST))
    fun postCustomer(@RequestBody customer: Customer) {
        customers[customer.id] = customer
    }

    @RequestMapping(value ="/customer/{id}", method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable("id") id : Int) = customers.remove(id)

    @RequestMapping(value = "/customer/", method = arrayOf(RequestMethod.PUT))
    fun putCustomer(@RequestBody customer: Customer) {
        customers.remove(customer.id)
        customers[customer.id] = customer
    }

    @RequestMapping(value = "/customers", method = arrayOf(RequestMethod.GET))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customers.filter{ it.value.name.contains(nameFilter, true) }
                    .map(Map.Entry<Int, Customer>::value).toList()

}
