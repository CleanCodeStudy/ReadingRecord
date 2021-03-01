package com.javabom.toby.chapter6.term.트랜잭션전파;

import org.springframework.transaction.annotation.Propagation;

public interface UpperService {
    void call(Propagation propagation) throws InterruptedException;
}
