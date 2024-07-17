package com.theone.reactive_programming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theone.reactive_programming.dao.CustomerDao;
import com.theone.reactive_programming.model.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;
    public List<Customer> getCustomes() {
        Long start = System.currentTimeMillis();
        List<Customer> customes = dao.getCustomes();
        System.out.println("Time taken to fetch customers traditional: " + (System.currentTimeMillis() - start) + " ms");
        return customes;
    }

    public Flux<Customer> getFluxCustomes() {
        Long start = System.currentTimeMillis();
        Flux<Customer> customes = dao.getFluxCustomes();
        System.out.println("Time taken to fetch customers reactive: " + (System.currentTimeMillis() - start) + " ms");
        return customes;
    }
}
