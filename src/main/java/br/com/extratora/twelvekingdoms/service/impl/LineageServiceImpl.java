package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import br.com.extratora.twelvekingdoms.repository.LineageRepository;
import br.com.extratora.twelvekingdoms.service.LineageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LineageServiceImpl implements LineageService {
    private final LineageRepository lineageRepository;

    public LineageServiceImpl(LineageRepository lineageRepository) {
        this.lineageRepository = lineageRepository;
    }

    @Override
    @Cacheable(value = "lineages")
    public List<IdNameDto> lineageList() {
        return lineageRepository.listBasicLineages();
    }

    @Override
    @Cacheable(value = "lineages", key = "#id")
    public LineageModel getLineage(UUID id) {
        return lineageRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }
}
