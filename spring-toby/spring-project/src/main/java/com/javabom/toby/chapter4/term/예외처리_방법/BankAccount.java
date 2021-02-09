package com.javabom.toby.chapter4.term.예외처리_방법;

import com.javabom.toby.chapter4.exception.CheckedException;
import com.javabom.toby.chapter4.exception.UncheckedException;

public class BankAccount {
    private int deposit;

    public BankAccount(final int deposit) {
        this.deposit = deposit;
    }

    public void tryWithDraw(final int money) throws CheckedException {
        if (deposit < money) {
            throw new CheckedException("잔액 부족");
        }
    }

    public int exceptionRecover(final int money) {
        int result;
        try {
            tryWithDraw(money);
            deposit -= money;
            result = money;
        } catch (CheckedException e) {
            System.out.println("잔액이 부족하여 현재 잔액을 인출합니다. - " + (deposit));
            result = deposit;
            deposit = 0;
        }

        return result;
    }

    public int exceptionAvoid(final int money) throws CheckedException {
        tryWithDraw(money);
        deposit -= money;
        return money;
    }

    public int exceptionTranslation(final int money) {
        int result;
        try {
            tryWithDraw(money);
            deposit -= money;
            result = money;
        } catch (CheckedException e) {
            //잔고가 0이면 RuntimeException으로
            if (deposit == 0) {
                throw new UncheckedException(e);
            }
            System.out.println("잔액이 부족하여 현재 잔액을 인출합니다. - " + (deposit));
            result = deposit;
            deposit = 0;
        }

        return result;
    }
}
