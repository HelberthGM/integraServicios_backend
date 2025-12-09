package com.lazarus.auth.repository;

import com.lazarus.auth.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {

    List<LoginLog> findByUserId(UUID userId);

    List<LoginLog> findByUserIdAndSuccess(UUID userId, Boolean success);

    List<LoginLog> findByLoginTimeBetween(OffsetDateTime start, OffsetDateTime end);
}
