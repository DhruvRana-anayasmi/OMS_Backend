package com.example.OMS.repository;

import com.example.OMS.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog,Long> {
}
