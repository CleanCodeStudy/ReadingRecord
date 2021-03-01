package com.javabom.toby.chapter6.term.트랜잭션전파;

import com.javabom.toby.chapter6.term.트랜잭션전파.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = PropagationConfiguration.class)
class PropagationTest {

    @Autowired
    private UpperTestService upperTestService;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("트랜잭션 전파")
    @ParameterizedTest
    @ValueSource(strings = {"REQUIRED", "REQUIRES_NEW"})
    void propagationTest(Propagation propagation) {
        upperTestService.call(propagation);
    }

    @DisplayName("Not Supported로 걸려있는 트랜잭션에서 exception이 발생하면 멈춰있던 트랜잭션도 롤백 된다.")
    @Test
    void propagationNotSupportedTest() {
        assertThatThrownBy(() -> upperTestService.call(Propagation.NOT_SUPPORTED));

        assertThat(teamRepository.findAll()).hasSize(0);
    }
}