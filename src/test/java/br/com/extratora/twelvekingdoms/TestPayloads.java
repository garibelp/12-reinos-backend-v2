package br.com.extratora.twelvekingdoms;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.RolesEnum;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.model.RoleModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public class TestPayloads {
    public static UUID UUID_1 = UUID.fromString("4c78842c-769b-4dc5-988a-234a438a4353");
    public static UUID UUID_2 = UUID.fromString("4c78842a-769b-4dc5-988a-234a438a4353");

    public static Page<BasicPlayerDto> getPlayerDtoPage() {
        return new PageImpl<>(List.of(getBasicPlayerDto()));
    }

    public static BasicPlayerDto getBasicPlayerDto() {
        return BasicPlayerDto.builder()
                .id(UUID_1)
                .username("username")
                .email("user@mail.com")
                .isActive(true)
                .build();
    }

    public static Page<BasicSheetDto> getBasicSheetDtoPage() {
        return new PageImpl<>(List.of(getBasicSheetDto()));
    }

    public static BasicSheetDto getBasicSheetDto() {
        return BasicSheetDto.builder()
                .id(UUID_1)
                .level(1)
                .isActive(true)
                .build();
    }

    public static Set<RoleModel> getRoleSet(boolean isAdmin) {
        Set<RoleModel> roleModels = new HashSet<>();
        roleModels.add(RoleModel.builder().id(UUID.randomUUID()).name(RolesEnum.ROLE_USER).build());
        if (isAdmin) {
            roleModels.add(RoleModel.builder().id(UUID.randomUUID()).name(RolesEnum.ROLE_ADMIN).build());
        }
        return roleModels;
    }

    public static PlayerModel getPlayerModel(UUID id, String username, boolean isAdmin, boolean isActive) {
        return PlayerModel.builder()
                .id(id)
                .username(username)
                .isActive(isActive)
                .roles(getRoleSet(isAdmin))
                .build();
    }

    public static UserDetailsImpl getUserDetailsAdmin() {
        return getUserDetails(UUID_1, "admin", true, true);
    }

    public static UserDetailsImpl getUserDetailsUser() {
        return getUserDetails(UUID_1, "user", false, true);
    }

    public static UserDetailsImpl getUserDetails(UUID id, String username, boolean isAdmin, boolean isActive) {
        return UserDetailsImpl.build(getPlayerModel(id, username, isAdmin, isActive));
    }

    public static SignupRequest getSignupRequest() {
        return SignupRequest.builder()
                .username("Test1")
                .password("Test@001")
                .firstName("Some")
                .lastName("Name")
                .email("valid@mail")
                .build();
    }

    public static CreateSheetRequest getValidCreateSheetRequest() {
        return CreateSheetRequest.builder()
                .name("Valid")
                .lineageId(UUID_1)
                .intelligence(DiceEnum.D4)
                .cunning(DiceEnum.D4)
                .tenacity(DiceEnum.D6)
                .celerity(DiceEnum.D8)
                .build();
    }

    public static Stream<CreateSheetRequest> getInvalidCreateSheetRequestStreamWithDiceValidation() {
        return Stream.concat(
                getInvalidCreateSheetRequestStream(),
                Stream.of(
                        CreateSheetRequest.builder()
                                .name("Valid")
                                .lineageId(UUID_1)
                                .intelligence(DiceEnum.D4)
                                .cunning(DiceEnum.D4)
                                .tenacity(DiceEnum.D4)
                                .celerity(DiceEnum.D4)
                                .build(),
                        CreateSheetRequest.builder()
                                .name("Valid")
                                .lineageId(UUID_1)
                                .intelligence(DiceEnum.D4)
                                .cunning(DiceEnum.D4)
                                .tenacity(DiceEnum.D4)
                                .celerity(DiceEnum.D6)
                                .build(),
                        CreateSheetRequest.builder()
                                .name("Valid")
                                .lineageId(UUID_1)
                                .intelligence(DiceEnum.D4)
                                .cunning(DiceEnum.D4)
                                .tenacity(DiceEnum.D6)
                                .celerity(DiceEnum.D6)
                                .build(),
                        CreateSheetRequest.builder()
                                .name("Valid")
                                .lineageId(UUID_1)
                                .intelligence(DiceEnum.D4)
                                .cunning(DiceEnum.D6)
                                .tenacity(DiceEnum.D6)
                                .celerity(DiceEnum.D6)
                                .build(),
                        CreateSheetRequest.builder()
                                .name("Valid")
                                .lineageId(UUID_1)
                                .intelligence(DiceEnum.D4)
                                .cunning(DiceEnum.D6)
                                .tenacity(DiceEnum.D6)
                                .celerity(DiceEnum.D8)
                                .build()
                )
        );
    }

    public static Stream<CreateSheetRequest> getInvalidCreateSheetRequestStream() {
        return Stream.of(
                CreateSheetRequest.builder().build(),
                CreateSheetRequest.builder()
                        .name("")
                        .build(),
                CreateSheetRequest.builder()
                        .name("Test")
                        .build(),
                CreateSheetRequest.builder()
                        .name("Test max characters!!")
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .intelligence(DiceEnum.D4)
                        .cunning(DiceEnum.D4)
                        .celerity(DiceEnum.D4)
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .intelligence(DiceEnum.D4)
                        .cunning(DiceEnum.D4)
                        .tenacity(DiceEnum.D4)
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .tenacity(DiceEnum.D4)
                        .cunning(DiceEnum.D4)
                        .celerity(DiceEnum.D4)
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .intelligence(DiceEnum.D4)
                        .tenacity(DiceEnum.D4)
                        .celerity(DiceEnum.D4)
                        .build()
        );
    }

    public static PlayerModel getPlayerModel(UUID id) {
        return PlayerModel
                .builder()
                .id(id)
                .build();
    }

    public static SheetModel getSheetModel(UUID playerId) {
        return SheetModel.builder()
                .id(UUID_1)
                .player(getPlayerModel(playerId))
                .build();
    }

    public static LineageModel getLineageModel() {
        return LineageModel.builder()
                .name("Anão")
                .build();
    }
}
