package com.qadr.customer;

import com.qadr.sharedlibrary.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired private CustomerService customerService;

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/count")
    public Long countAll(){
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return customerService.count();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String id){
        customerService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public Customer byId(@PathVariable("id") String id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/find/{keyword}")
    public List<Customer> byAddress(@PathVariable("keyword") String keyword){
        return customerService.findCustomer(keyword);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

}
