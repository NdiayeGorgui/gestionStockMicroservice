package com.gorgui.billingservice.restcontroller;

import com.gorgui.billingservice.model.Bill;
import com.gorgui.billingservice.repository.BillRepository;
import com.gorgui.billingservice.repository.ProductItemRepository;
import com.gorgui.billingservice.service.CustomerService;
import com.gorgui.billingservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(productService.findProductById(pi.getProductId()));
        });
        return bill;
    }
}
