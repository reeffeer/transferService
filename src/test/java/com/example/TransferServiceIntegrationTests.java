package com.example;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceIntegrationTests {
    Account sender = new Account();
    Account receiver = new Account();

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @Test
    void transferServiceTransferAmountTest() {
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(receiver));

        transferService.transferMoney(1, 2, new BigDecimal(100));

        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));
    }
}
