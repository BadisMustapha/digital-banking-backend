package org.badis.digitbankbackend.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badis.digitbankbackend.entities.BankAccount;
import org.badis.digitbankbackend.entities.CurrentAccount;
import org.badis.digitbankbackend.entities.SavingAccount;
import org.badis.digitbankbackend.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BankService {

    final BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=
                bankAccountRepository.findById("560ddce3-652d-4551-a68f-9b4f61404537").orElse(null);
        if(bankAccount!=null) {
            log.info("*****************************");
            log.info(bankAccount.getId());
            log.info("{}",bankAccount.getBalance());
            log.info("{}",bankAccount.getStatus());
            log.info("{}",bankAccount.getCreatedAt());
            log.info("{}",bankAccount.getCustomer().getName());
            log.info("{}",bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                log.info("Over Draft=> {}" , ((CurrentAccount) bankAccount).getOverdraft());
            } else if (bankAccount instanceof SavingAccount) {
                log.info("Rate=> {}" , ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> log.info("{} \t {} \t {}",op.getType(), op.getOperationDate() , op.getAmount()));
        }
    }
}
