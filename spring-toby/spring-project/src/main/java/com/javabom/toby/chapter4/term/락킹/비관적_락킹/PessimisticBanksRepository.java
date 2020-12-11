package com.javabom.toby.chapter4.term.락킹.비관적_락킹;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface PessimisticBanksRepository extends JpaRepository<PessimisticBanks, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    Optional<PessimisticBanks> findById(Long id);
}
