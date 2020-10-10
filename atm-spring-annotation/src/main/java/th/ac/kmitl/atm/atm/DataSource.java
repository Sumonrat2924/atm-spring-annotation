package th.ac.kmitl.atm.atm;

import java.util.Map;

public interface DataSource {
    Map<Integer,Customer> readCustomers();
}
