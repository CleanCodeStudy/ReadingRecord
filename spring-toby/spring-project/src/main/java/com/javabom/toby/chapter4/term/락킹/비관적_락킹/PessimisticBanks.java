package com.javabom.toby.chapter4.term.락킹.비관적_락킹;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Version;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PessimisticBanks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int money;

    public PessimisticBanks(final int money) {
        this.money = money;
    }

    public int withDrawSleep(final int money, final long sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
        this.money -= money;

        return this.money;
    }
}
