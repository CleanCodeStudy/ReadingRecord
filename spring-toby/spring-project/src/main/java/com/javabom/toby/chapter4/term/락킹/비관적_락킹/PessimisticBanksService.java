package com.javabom.toby.chapter4.term.락킹.비관적_락킹;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PessimisticBanksService {
    private final PessimisticBanksRepository pessimisticBanksRepository;

    @Transactional
    public int withDrawMoney(final int money, final long sleepTime) throws InterruptedException {
        PessimisticBanks banks = pessimisticBanksRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        return banks.withDrawSleep(money, sleepTime);
    }
}
