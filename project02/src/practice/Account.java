package practice;

/**
 * @create 2021/12/20 - 10:06
 */
class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        if (amt >= 0) {
            balance += amt;
        }
        System.out.println("存钱成功");
    }

    public void withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            System.out.println("取钱成功");
        } else {
            System.out.println("余额不足");
        }
    }

}

class Customer {
    private String name;
    private Account account;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

class Bank {
    private Customer[] customers; // 数组存放多个客户
    private int numberOfCustomers;

    // 结构体，每次创建new Bank都要初始化客户数组
    public Bank() {
        customers = new Customer[10];
    }

    public Bank(int number_of_customer) {
        customers = new Customer[number_of_customer];
    }

    // 方法，加入一个new customer，同时使客户数+1
    public void addCustomer(String name) {
        Customer cus = new Customer(name);
        customers[numberOfCustomers] = cus;
        numberOfCustomers++;
        // 或者直接 customers[numberOfCustomers++] = cus;
    }

    // 返回customer数量
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    // 得到某具体index上的customer对象
    public Customer getCustomer(int index) {
        if (index >= 0 && index < numberOfCustomers) {
            return customers[index];
        } else {
            System.out.println("invalid index");
            return null;
        }
    }
}
