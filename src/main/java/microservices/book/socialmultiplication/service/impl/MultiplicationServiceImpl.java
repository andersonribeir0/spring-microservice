package microservices.book.socialmultiplication.service.impl;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.service.MultiplicationService;
import microservices.book.socialmultiplication.service.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        boolean isCorrect = resultAttempt.getResultAttempt() ==
                 resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct!!");

        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(resultAttempt.getUser(),
                resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(),
                isCorrect);

        return isCorrect;
    }
}
