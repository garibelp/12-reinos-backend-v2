package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.repository.LineageRepository;
import br.com.extratora.twelvekingdoms.service.impl.LineageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.UUID_1;
import static br.com.extratora.twelvekingdoms.TestPayloads.getLineageModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LineageServiceTests {
    @Mock
    private LineageRepository lineageRepository;
    @InjectMocks
    private LineageServiceImpl lineageService;

    @Test
    void givenLineageList_whenCalled_thenShouldCallRepository() {
        lineageService.lineageList();

        verify(lineageRepository, times(1)).listBasicLineages();
    }

    @Test
    void givenGetLineageCalled_whenUUIDNotFound_thenShouldThrowDataNotFoundException() {
        when(lineageRepository.findById(UUID_1)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> lineageService.getLineage(UUID_1)
        );

        verify(lineageRepository, times(1)).findById(UUID_1);
    }

    @Test
    void givenGetLineageCalled_whenUUIDFound_thenShouldReturnData() {
        var lineageModel = getLineageModel();
        when(lineageRepository.findById(UUID_1)).thenReturn(Optional.of(lineageModel));

        var response = lineageService.getLineage(UUID_1);

        assertEquals(lineageModel, response);
        verify(lineageRepository, times(1)).findById(UUID_1);
    }
}
