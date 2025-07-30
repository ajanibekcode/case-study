package com.myapp.desk.service;

import com.myapp.desk.domain.Trade;
import com.myapp.desk.respository.TradeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
    @Cacheable(value="trades", key="#id")
    public Optional<Trade> getTradeById(Long id) {
        return tradeRepository.findById(id);
    }

//    custom from trade repo
    public Trade getTradeByTradeId(String tradeId) {
        return tradeRepository.findByTradeId(tradeId);
    }
    @CacheEvict(value = "trades", key = "#id")
    public void deleteTradeById(Long id) {
        tradeRepository.deleteById(id);
    }
}

