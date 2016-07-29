package rw.viden.volcanoproject.ticketing.model;

/**
 * Created by nic on 7/28/16.
 */
public class CustomerLigne {
    Customer customer;
    Ligne ligne;
    int count;

    public CustomerLigne(Customer customer, Ligne ligne, int count) {
        this.customer = customer;
        this.ligne = ligne;
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
