package com.myapp.desk.resource;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.service.InstrumentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instruments")
public class InstrumentResource {
    private final InstrumentService instrumentService;

    public InstrumentResource(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) {
        instrument = instrumentService.save(instrument);
        return new ResponseEntity<>(instrument, HttpStatus.OK);
    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        return ResponseEntity.ok(instrumentService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable Long id) {
        return instrumentService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<Instrument> getInstrumentBySymbol(@PathVariable String symbol) {
        Instrument instrument = instrumentService.findBySymbol(symbol);
        return instrument != null ? ResponseEntity.ok(instrument) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Instrument> updateInstrument(@PathVariable Long id, @RequestBody Instrument updatedInstrument) {
        return instrumentService.findById(id)
                .map(i -> {
                    i.setName(updatedInstrument.getName());
                    i.setSymbol(updatedInstrument.getSymbol());
                    i.setIsin(updatedInstrument.getIsin());
                    return ResponseEntity.ok(instrumentService.save(i));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
//    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
        if (instrumentService.findById(id).isPresent()) {
            instrumentService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}