package com.javabom.toby.chapter6.term.트랜잭션전파;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class UpperTestService implements UpperService {
    private final PropagationService propagationTestService;

    @Transactional
    public void call(Propagation propagation) throws InterruptedException {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        switch (propagation) {
            case REQUIRED:
                propagationTestService.required();
                break;
            case REQUIRES_NEW:
                propagationTestService.requiresNew();
                break;
            case NOT_SUPPORTED:
                propagationTestService.notSupported();
                break;
        }
    }
}
