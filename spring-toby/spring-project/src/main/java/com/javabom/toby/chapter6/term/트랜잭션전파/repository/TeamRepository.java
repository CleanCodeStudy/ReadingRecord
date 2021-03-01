package com.javabom.toby.chapter6.term.트랜잭션전파.repository;

import com.javabom.toby.chapter6.term.트랜잭션전파.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
