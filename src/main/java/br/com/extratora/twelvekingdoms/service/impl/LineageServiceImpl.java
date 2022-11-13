package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicLineageDto;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import br.com.extratora.twelvekingdoms.repository.LineageRepository;
import br.com.extratora.twelvekingdoms.service.LineageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LineageServiceImpl implements LineageService {
    private final LineageRepository lineageRepository;

    public LineageServiceImpl(LineageRepository lineageRepository) {
        this.lineageRepository = lineageRepository;
    }

    @Override
    public List<BasicLineageDto> lineageList() {
        return lineageRepository.listBasicLineages();
    }

    @Override
    public LineageModel getLineage(UUID id) {
        Optional<LineageModel> lineageOpt = lineageRepository.findById(id);

        if (lineageOpt.isEmpty()) {
            throw new DataNotFoundException();
        }

        return lineageOpt.get();
    }
}
