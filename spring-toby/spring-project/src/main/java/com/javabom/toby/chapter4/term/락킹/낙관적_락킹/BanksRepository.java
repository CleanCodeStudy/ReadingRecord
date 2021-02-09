package com.javabom.toby.chapter4.term.락킹.낙관적_락킹;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BanksRepository extends JpaRepository<Banks, Long> {
}
