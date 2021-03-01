package com.javabom.toby.chapter6.term.트랜잭션전파;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class PropagationTestService implements PropagationService {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void required() {
        printTransactionInfo();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void requiresNew() {
        printTransactionInfo();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void notSupported() {

        printTransactionInfo();
    }

    private void printTransactionInfo() {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
    }
}
