package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.model.WoundsModel;
import br.com.extratora.twelvekingdoms.repository.WoundRepository;
import br.com.extratora.twelvekingdoms.service.WoundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WoundServiceImpl implements WoundService {
    private final WoundRepository woundRepository;

    public WoundServiceImpl(WoundRepository woundRepository) {
        this.woundRepository = woundRepository;
    }

    @Override
    public List<WoundsModel> list() {
        return woundRepository.findAll();
    }
}
