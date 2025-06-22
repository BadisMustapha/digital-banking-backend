package org.badis.digitbankbackend;

import org.badis.digitbankbackend.entities.AccountOperation;
import org.badis.digitbankbackend.entities.CurrentAccount;
import org.badis.digitbankbackend.entities.Customer;
import org.badis.digitbankbackend.entities.SavingAccount;
import org.badis.digitbankbackend.enums.AccountStatus;
import org.badis.digitbankbackend.enums.OperationType;
import org.badis.digitbankbackend.repositories.AccountOperationRepository;
import org.badis.digitbankbackend.repositories.BankAccountRepository;
import org.badis.digitbankbackend.repositories.CustomerRepository;
import org.badis.digitbankbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitBankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankService bankService){
        return args -> {
           bankService.consulter();
        };
    }

   // @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Mustapha","Noureddine","Maryam").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@yopmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*30000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverdraft(5000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*30000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(4.7);
                bankAccountRepository.save(savingAccount);

            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i <10 ; i++) {
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }

            });
        };

    }

}
