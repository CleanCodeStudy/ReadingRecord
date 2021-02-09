package com.javabom.toby.chapter4.term.락킹.비관적_락킹;

import org.hibernate.PessimisticLockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PessimisticBanksTest {

    @Autowired
    private PessimisticBanksRepository pessimisticBanksRepository;

    @Autowired
    private PessimisticBanksService pessimisticBanksService;

    private CountDownLatch testLatch;
    Runnable runnable1 = () -> {
        try {
            pessimisticBanksService.withDrawMoney(1000, 3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            testLatch.countDown();
        }
    };
    Runnable runnable2 = () -> {
        assertThatThrownBy(() -> pessimisticBanksService.withDrawMoney(1100, 200))
                .isInstanceOf(PessimisticLockingFailureException.class);

        testLatch.countDown();
    };

    @BeforeEach
    void setUp() {
        pessimisticBanksRepository.save(new PessimisticBanks(2000));
        testLatch = new CountDownLatch(2);
    }

    @DisplayName("비관적 락킹 테스트, 비관적 락이 걸려있는 Entity를 update하려고 시도시 exception throw")
    @Test
    void name() throws InterruptedException {
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        testLatch.await();
    }
}