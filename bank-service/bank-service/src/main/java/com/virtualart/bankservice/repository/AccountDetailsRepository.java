package com.virtualart.bankservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualart.bankservice.entity.AccountDetailsEntity;
@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity,String> {
	AccountDetailsEntity findByAccountNumber(String accountNumber);
}
