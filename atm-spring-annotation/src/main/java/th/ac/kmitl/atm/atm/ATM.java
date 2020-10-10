package th.ac.kmitl.atm.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ATM {
    private Bank bank;
    private Customer loginCustomer;
    @Autowired
    public ATM(){
        this.bank = new Bank("My Bank");
        this.loginCustomer = null;
    }
    public String validateCustomer(int id,int pin) {
        //ดูว่า id ตรงกับที่มีใน bank หรือไม่
        Customer customer = bank.findCustomer(id);

        //ถ้า id ตรงจะตรวจสอบ pinของลูกค้าว่าตรงกับที่เก็บไว้หรือไม่
        if (customer != null && customer.checkPin(pin)) {
            loginCustomer = customer;
            return customer.getName();
        }
        return null;
    }

    //delegation
    public void deposit(double amount){
        loginCustomer.getAccount().deposit(amount);
    }
    public void withdraw(double amount){
        loginCustomer.getAccount().deposit(amount);
    }

    public double getBalance(){
        return loginCustomer.getAccount().getBalance();
    }

    public void transfer(int receivingId,double amount){
        Customer receivingCustomer = bank.findCustomer(receivingId);
        loginCustomer.getAccount().withdraw(amount);
        receivingCustomer.getAccount().deposit(amount);
    }
    public void end(){
        loginCustomer = null;
    }
}