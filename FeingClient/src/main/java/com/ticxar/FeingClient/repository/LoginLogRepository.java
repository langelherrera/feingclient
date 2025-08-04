package com.ticxar.FeingClient.repository;

import com.ticxar.FeingClient.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog,Long> {
}
