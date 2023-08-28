package com.virtualart.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualart.bankservice.entity.TransactionEntity;
@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionEntity, Integer> {
}
