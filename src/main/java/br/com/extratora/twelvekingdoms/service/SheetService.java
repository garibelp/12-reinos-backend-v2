package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface SheetService {
    SheetModel createSheet(UserDetailsImpl user, CreateSheetRequest request);

    SheetModel getSheet(UserDetailsImpl user, UUID id);

    Page<BasicSheetDto> sheetsPaginated(
            UserDetailsImpl user,
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            SheetSortEnum sortField,
            boolean usePlayerProfile,
            String nameFilter);

    void deleteSheet(UUID id, UserDetailsImpl user);

    void updateCurrentPoints(UserDetailsImpl user, UUID id, UpdateSheetCurrentPointsRequest request);

    void levelUp(UUID id, UserDetailsImpl user);
}
