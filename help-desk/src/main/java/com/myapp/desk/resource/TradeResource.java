package com.myapp.desk.resource;

import com.myapp.desk.domain.Trade;
import com.myapp.desk.service.TradeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trades")
public class TradeResource {

    private final TradeService tradeService;

    public TradeResource(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {

            Trade saved = tradeService.saveTrade(trade);
            return new ResponseEntity<>(saved, HttpStatus.OK);

    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getAllTrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return tradeService.getTradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/trade-id/{tradeId}")
    public ResponseEntity<Trade> getTradeByTradeId(@PathVariable String tradeId) {
        Trade trade = tradeService.getTradeByTradeId(tradeId);
        return trade != null ? ResponseEntity.ok(trade) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @RequestBody Trade updatedTrade) {
        return tradeService.getTradeById(id)
                .map(i -> {
                    i.setTradeId(updatedTrade.getTradeId());
                    i.setInstrument(updatedTrade.getInstrument());
                    i.setQuantity(updatedTrade.getQuantity());
                    i.setPrice(updatedTrade.getPrice());
                    i.setTradeDate(updatedTrade.getTradeDate());
                    i.setSourceSystem(updatedTrade.getSourceSystem());
                    return ResponseEntity.ok(tradeService.saveTrade(i));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
//        System.out.println("Deleting trade with ID: " + id);
        if (tradeService.getTradeById(id).isPresent()) {
            tradeService.deleteTradeById(id);
//            System.out.println("Deleted trade with ID: " + id);
            return ResponseEntity.ok().build();
        } else {
//            System.out.println("Trade not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
}
