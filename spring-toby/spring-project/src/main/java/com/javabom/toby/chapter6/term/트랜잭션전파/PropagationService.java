package com.javabom.toby.chapter6.term.트랜잭션전파;

public interface PropagationService {
    void required();

    void requiresNew();

    void notSupported();
}
