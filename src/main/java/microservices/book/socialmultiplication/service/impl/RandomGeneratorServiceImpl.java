package microservices.book.socialmultiplication.service.impl;

import microservices.book.socialmultiplication.service.RandomGeneratorService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    private static final int MIN_FACTOR = 11;
    private static final int MAX_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAX_FACTOR - MIN_FACTOR)+1) + MIN_FACTOR;
    }
}
