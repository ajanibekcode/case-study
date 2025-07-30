package com.myapp.desk.service;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.respository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Cacheable(value = "instruments", key = "#id")
    public Optional<Instrument> findById(Long id) {
        return instrumentRepository.findById(id);
    }

    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @CacheEvict(value = "instruments", key = "#id")
    public void deleteById(Long id) {
        instrumentRepository.deleteById(id);
    }

    public Instrument findBySymbol(String symbol) {
        return instrumentRepository.findBySymbol(symbol);
    }
}

