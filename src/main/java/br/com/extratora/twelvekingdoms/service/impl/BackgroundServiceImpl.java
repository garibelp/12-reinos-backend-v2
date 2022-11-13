package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.model.BackgroundModel;
import br.com.extratora.twelvekingdoms.repository.BackgroundRepository;
import br.com.extratora.twelvekingdoms.service.BackgroundService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundServiceImpl implements BackgroundService {
    private final BackgroundRepository backgroundRepository;

    public BackgroundServiceImpl(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
    }

    @Override
    @Cacheable(value = "backgrounds")
    public List<BackgroundModel> backgroundList() {
        return backgroundRepository.findAll();
    }
}
