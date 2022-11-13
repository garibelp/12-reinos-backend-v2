package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicLineageDto;
import br.com.extratora.twelvekingdoms.model.LineageModel;

import java.util.List;
import java.util.UUID;

public interface LineageService {
    List<BasicLineageDto> lineageList();

    LineageModel getLineage(UUID id);
}
