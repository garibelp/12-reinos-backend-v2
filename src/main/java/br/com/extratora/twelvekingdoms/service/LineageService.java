package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicIdNameDto;
import br.com.extratora.twelvekingdoms.model.LineageModel;

import java.util.List;
import java.util.UUID;

public interface LineageService {
    List<BasicIdNameDto> lineageList();

    LineageModel getLineage(UUID id);
}
