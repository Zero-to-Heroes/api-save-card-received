package com.zerotoheroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardStatService {

    private CardStatRepository repository;

    @Autowired
    public CardStatService(CardStatRepository repository) {
        this.repository = repository;
    }

    public void createCardStat(CardStat cardStat) {
        repository.saveCardStat(cardStat);
    }
}
