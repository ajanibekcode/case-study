package com.myapp.desk.service;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentService(InstrumentService instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    public Optional<Instrument> findById(Long id) {
        return instrumentRepository.findById(id);
    }

    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public void deleteById(Long id) {
        instrumentRepository.deleteById(id);
    }

    public Instrument findBySymbol(String symbol) {
        return instrumentRepository.findBySymbol(symbol);
    }
}

