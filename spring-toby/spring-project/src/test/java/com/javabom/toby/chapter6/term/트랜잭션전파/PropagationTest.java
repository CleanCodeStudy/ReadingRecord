package com.javabom.toby.chapter6.term.트랜잭션전파;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
class PropagationTest {

    @Autowired
    private UpperService upperTestService;

    @DisplayName("트랜잭션 전파")
    @ParameterizedTest
    @ValueSource(strings = {"REQUIRED", "REQUIRES_NEW", "NOT_SUPPORTED"})
    void propagationTest(Propagation propagation) throws Exception {
        upperTestService.call(propagation);
    }
}