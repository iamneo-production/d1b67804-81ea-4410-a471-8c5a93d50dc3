package com.hackathon.artist.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class ArtworkTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	private Double amount; 
	private int creditedTo;
	private int debitedBy;
	private String transactionNo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
}
