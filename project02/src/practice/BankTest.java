package practice;

/**
 * @create 2021/12/20 - 11:35
 */
public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank(10);
        bank.addCustomer("Chen");
        bank.getCustomer(0).setAccount(new Account(2000));
        bank.getCustomer(0).getAccount().withdraw(1000);
        bank.getCustomer(0).getAccount().deposit(3000);
        System.out.println(bank.getCustomer(0).getAccount().getBalance());
    }
}
