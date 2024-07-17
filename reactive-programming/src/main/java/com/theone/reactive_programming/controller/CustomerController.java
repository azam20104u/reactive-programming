package com.theone.reactive_programming.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theone.reactive_programming.model.Customer;
import com.theone.reactive_programming.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    // Get all customers
    //traditional way to process data which is slow and block the threads
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomes();
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersFlux() {
        return customerService.getFluxCustomes();
    }
}
