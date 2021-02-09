package com.javabom.toby.chapter4.term.락킹.낙관적_락킹;

import com.javabom.toby.chapter4.term.락킹.낙관적_락킹.Banks;
import com.javabom.toby.chapter4.term.락킹.낙관적_락킹.BanksRepository;
import com.javabom.toby.chapter4.term.락킹.낙관적_락킹.BanksService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BanksTest {

    @Autowired
    private BanksRepository banksRepository;

    @Autowired
    private BanksService banksService;

    private CountDownLatch latch;
    private CountDownLatch testLatch;
    Runnable runnable1 = () -> {

        assertThatThrownBy(() -> banksService.withDrawMoney(1000, 1000))
                .isInstanceOf(OptimisticLockingFailureException.class);

        testLatch.countDown();
    };
    Runnable runnable2 = () -> {
        try {
            banksService.withDrawMoney(1100, 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            testLatch.countDown();
        }
    };
    private Banks banks;

    @BeforeEach
    void setUp() {
        banks = banksRepository.save(new Banks(2000));
        latch = new CountDownLatch(1);
        testLatch = new CountDownLatch(2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void name1() throws InterruptedException {
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        testLatch.await();
    }
}