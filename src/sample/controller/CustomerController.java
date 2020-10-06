package sample.controller;

import sample.Repository.CustomerRepository;
import sample.Repository.ProductRepository;
import sample.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    private List<String> types;
    private Customer customer;

    public List<String> getTypeList() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        types = customerRepository.getTypes();
        return types;
    }

    public void addCustomerToBD(int id, String type, String firstN, String secondN, String region, String street, int house, int bankN) throws SQLException {
        customer = new Customer(id, type, firstN, secondN, region, street, house, bankN);
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.addCustomer(customer);
    }

}
