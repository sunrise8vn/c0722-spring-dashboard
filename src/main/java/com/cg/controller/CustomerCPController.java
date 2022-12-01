package com.cg.controller;

import com.cg.model.Customer;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/cp/customers")
public class CustomerCPController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String showCPListCustomerPage(@RequestParam String searchKey, Model model) {
        List<Customer> customers;

        if (searchKey != null) {
            searchKey = "%" + searchKey + "%";

            customers = customerService.findAllByFullNameLikeOrEmailLikeOrPhoneLike(searchKey, searchKey, searchKey);
        }
        else {
            customers = customerService.findAll();
        }

        model.addAttribute("customers", customers);

        return "cp/customer/list";
    }

    @GetMapping("/create")
    public String showCPCreateCustomerPage(ModelMap modelMap) {
        modelMap.addAttribute("customer", new Customer());
        return "cp/customer/create";
    }

    @GetMapping("/edit/{id}")
    public String showCPEditCustomerPage(ModelMap modelMap, @PathVariable Long id) {

        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelMap.addAttribute("error", "Customer ID not valid");
        }

        modelMap.addAttribute("customer", customerOptional.get());

        return "cp/customer/edit";
    }

    @PostMapping("/create")
    public String doCreateCustomer(ModelMap modelMap, @ModelAttribute Customer customer) {

        customerService.save(customer);

        modelMap.addAttribute("customer", new Customer());
        return "cp/customer/create";
    }
}
