package th.ac.kmitl.atm.atm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Bank {
    private String name; //attribute

    private Map<Integer,Customer> customers; //composition
    private DataSourceDB dataSource;         //composition
    @Autowired
    public Bank(@Value("${bankname}") String name) {
        this.name = name;
        this.dataSource = new DataSourceDB();
        this.customers = dataSource.readCustomer();
    }

    public void registerCustomer(Customer customer){
        customers.put(customer.getId(),customer);
    }

    public Customer findCustomer(int id){
        return customers.get(id);
    }

    public String getName() {
        return name;
    }
}

