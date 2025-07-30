package com.myapp.desk.respository;

import com.myapp.desk.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TradeRepository  extends JpaRepository<Trade, Long> {
    Trade findByTradeId(String tradeId);
}
