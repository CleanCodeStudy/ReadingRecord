package com.javabom.toby.chapter4.term.예외처리_방법;

import com.javabom.toby.chapter4.exception.CheckedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @DisplayName("예외 복구 메소드로 부족한 금액을 제외한 금액을 반환")
    @Test
    void exceptionRecover() {
        BankAccount bankAccount = new BankAccount(1000);

        int money = bankAccount.exceptionRecover(2000);

        assertThat(money).isEqualTo(1000);
    }

    @DisplayName("예외 회피 메소드로 호출한 메소드에 예외 처리를 위임")
    @Test
    void exceptionAvoid() {
        BankAccount bankAccount = new BankAccount(1000);
        int money;
        try {
            money = bankAccount.exceptionAvoid(2000);

        } catch (CheckedException e) {
            money = -1;
        }

        assertThat(money).isEqualTo(-1);
    }

    @DisplayName("예외 전환 메소드로 checked exception을 unchecked exception으로 전환")
    @Test
    void exceptionTranslation() {
        BankAccount bankAccount = new BankAccount(0);
        assertThatThrownBy(() -> bankAccount.exceptionTranslation(1000))
                .isInstanceOf(RuntimeException.class);
    }
}