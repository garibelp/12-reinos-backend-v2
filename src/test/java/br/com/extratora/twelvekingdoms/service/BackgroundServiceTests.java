package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.repository.BackgroundRepository;
import br.com.extratora.twelvekingdoms.service.impl.BackgroundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BackgroundServiceTests {
    @Mock
    private BackgroundRepository backgroundRepository;
    @InjectMocks
    private BackgroundServiceImpl backgroundService;

    @Test
    void givenLineageList_whenCalled_thenShouldCallRepository() {
        backgroundService.backgroundList();

        verify(backgroundRepository, times(1)).findAll();
    }
}
