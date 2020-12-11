package com.javabom.toby.chapter4.term.락킹.낙관적_락킹;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BanksService {
    private final BanksRepository banksRepository;

    @Transactional
    public int withDrawMoney(final int money, final long sleepTime) throws InterruptedException {
        Banks banks = banksRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        int result = banks.withDrawSleep(money, sleepTime);

        System.out.println(banks);

        return result;
    }
}
