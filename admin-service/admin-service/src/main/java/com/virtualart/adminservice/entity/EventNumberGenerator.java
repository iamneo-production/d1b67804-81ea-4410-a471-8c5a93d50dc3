package com.virtualart.adminservice.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
public class EventNumberGenerator implements IdentifierGenerator {
	private static final long serialVersionUID = 1L;
	private static final String EVENT_NUMBER_CHARACTERS="EVENT";
    private static final Random RANDOM_EVENT_NUMBER_GENERATOR = new Random();
    private static final int EVENT_NUMBER_LENGTH=5;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder eventId = new StringBuilder(EVENT_NUMBER_LENGTH);
        List<Integer> randomNumbers=new ArrayList<>();
        for(int random=0;random<=4;random++){
            randomNumbers.add(random);
        }
        randomNumbers.subList(0,randomNumbers.size()).forEach(random->{
        	eventId.append(RANDOM_EVENT_NUMBER_GENERATOR.nextInt(10));
        });
        return EVENT_NUMBER_CHARACTERS+eventId.toString();
    }
}