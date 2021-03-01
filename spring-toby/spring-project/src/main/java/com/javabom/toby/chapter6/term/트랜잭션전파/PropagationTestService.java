package com.javabom.toby.chapter6.term.트랜잭션전파;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class PropagationTestService {
    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        printTransactionInfo();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() {
        printTransactionInfo();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
        printTransactionInfo();
        throw new IllegalStateException();
    }

    private void printTransactionInfo() {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
    }
}
