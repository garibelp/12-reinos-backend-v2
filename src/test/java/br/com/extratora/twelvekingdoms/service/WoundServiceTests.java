package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.repository.WoundRepository;
import br.com.extratora.twelvekingdoms.service.impl.WoundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class WoundServiceTests {
    @Mock
    private WoundRepository woundRepository;
    @InjectMocks
    private WoundServiceImpl woundService;

    @Test
    void givenWoundList_whenCalled_thenShouldCallRepository() {
        woundService.list();

        verify(woundRepository, times(1)).findAll();
    }
}
