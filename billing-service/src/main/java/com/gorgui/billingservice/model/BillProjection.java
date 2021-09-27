package com.gorgui.billingservice.model;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

//pour recuperer une facture avec le client et les produits
@Projection(name = "fullBill",types = Bill.class)
public interface BillProjection {
    public  Long getId();
    public Date getBillingDate();
    public  Long getCustomerId();
    public Collection<ProductItem> getProductItems();

}
