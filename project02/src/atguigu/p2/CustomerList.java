package atguigu.p2;

public class CustomerList {
    // fields
    private Customer[] customers;
    private int total = 0;

    // constructor
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    // methods
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) return false;
        
        customers[total++] = customer;
        return true;
    }
     
    public boolean replaceCustomer(int index, Customer cust) {
        if (index < 0 || index >= total) return false;
        
        customers[index] = cust;
        return true;
    }

    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) return false;
        
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }
        
        customers[--total] = null;

        return true;
    }

    // 返回所有有效Customer的数组
    public Customer[] getAllCustomers() {
        Customer[] custs = new Customer[total];
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }

    // 得到总customers数
    public int getTotal() {
        return total;
    }

    // 得到某index上的customer
    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) return null;
        
        return customers[index];
    }
}
