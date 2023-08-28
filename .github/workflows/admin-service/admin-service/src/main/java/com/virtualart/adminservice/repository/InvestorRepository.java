package com.virtualart.adminservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualart.adminservice.entity.Investor;
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer>{
	
}
