package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import br.com.extratora.twelvekingdoms.model.LineageModel;

import java.util.List;
import java.util.UUID;

public interface LineageService {
    List<IdNameDto> lineageList();

    LineageModel getLineage(UUID id);
}
