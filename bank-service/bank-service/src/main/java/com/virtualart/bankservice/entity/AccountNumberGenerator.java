package com.virtualart.bankservice.entity;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AccountNumberGenerator implements IdentifierGenerator {
	private static final long serialVersionUID = 1L;
	private static final String ACCOUNT_NUMBER_CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM_ACCOUNT_NUMBER_GENERATOR = new Random();
    private static final int ACCOUNT_NUMBER_LENGTH=10;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        List<Integer> randomNumbers=new ArrayList<>();
        for(int random=0;random<=9;random++){
            randomNumbers.add(random);
        }
        randomNumbers.subList(0,4).forEach(random->{
            accountNumber.append(ACCOUNT_NUMBER_CHARACTERS.charAt(RANDOM_ACCOUNT_NUMBER_GENERATOR.
                    nextInt(ACCOUNT_NUMBER_CHARACTERS.length())));
        });
        randomNumbers.subList(4,randomNumbers.size()).forEach(random->{
            accountNumber.append(RANDOM_ACCOUNT_NUMBER_GENERATOR.nextInt(10));
        });
        return accountNumber.toString();
    }
}
