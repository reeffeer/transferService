package com.example;

import com.example.exception.AccountNotFoundException;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {
    Account sender = new Account();
    Account receiver = new Account();

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @DisplayName("Test the amount is transferred from one account to another if no exception occurs")
    @Test
    public void moneyTransferPositiveTest() {
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepository.findById(receiver.getId())).willReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal(100));

        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));
    }

    @DisplayName("Test appear AccontNotFoundException if the sender account is not found")
    @Test
    public void moneyTransferReceiverAccountNotFoundTest() {
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> transferService.transferMoney(1, 2, new BigDecimal(100)));

        verify(accountRepository, never()).changeAmount(anyLong(), any());
    }
}