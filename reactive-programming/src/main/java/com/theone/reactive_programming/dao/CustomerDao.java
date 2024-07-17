package com.theone.reactive_programming.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.theone.reactive_programming.model.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simulating a slow database operation traditional way
    //until and unless all the data process you will not get result
    public List<Customer> getCustomes() {
        return IntStream.range(1, 11)
        .peek(CustomerDao::sleepExecution)
        .peek(i->System.out.println("processing customer traditional"+i))
        .mapToObj(i -> new Customer(i,"Customer"+i))
        .collect(Collectors.toList());
    }

    //reactive asynchroneous and non-blocking way
    //if single object processed you will get immediately result
    public Flux<Customer> getFluxCustomes() {
        return Flux.range(1,11)
        .delayElements(Duration.ofSeconds(1))
        .doOnNext(i->System.out.println(" processing customer reactive "+i))
        .map(i -> new Customer(i,"Customer"+i));
    }

}
