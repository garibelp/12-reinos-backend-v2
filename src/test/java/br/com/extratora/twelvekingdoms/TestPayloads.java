package br.com.extratora.twelvekingdoms;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.*;
import br.com.extratora.twelvekingdoms.enums.DeathRollStatus;
import br.com.extratora.twelvekingdoms.enums.Dice;
import br.com.extratora.twelvekingdoms.enums.Roles;
import br.com.extratora.twelvekingdoms.model.*;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Stream;

public class TestPayloads {
    public static UUID UUID_1 = UUID.fromString("4c78842c-769b-4dc5-988a-234a438a4353");
    public static UUID UUID_2 = UUID.fromString("12e3aa6c-69ee-48c4-9ab6-96d2ac11a7ca");
    public static UUID UUID_3 = UUID.fromString("920cf82c-c767-48ab-980e-f510bb136556");

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

    public static Set<RoleModel> getRoleSet(boolean isAdmin, boolean isGm) {
        Set<RoleModel> roleModels = new HashSet<>();
        roleModels.add(RoleModel.builder().id(UUID.randomUUID()).name(Roles.ROLE_USER).build());
        if (isAdmin) {
            roleModels.add(RoleModel.builder().id(UUID.randomUUID()).name(Roles.ROLE_ADMIN).build());
        }
        if (isGm) {
            roleModels.add(RoleModel.builder().id(UUID.randomUUID()).name(Roles.ROLE_GM).build());
        }
        return roleModels;
    }

    public static PlayerModel getPlayerModel(UUID id, String username, boolean isAdmin, boolean isGm, boolean isActive) {
        return PlayerModel.builder()
                .id(id)
                .username(username)
                .isActive(isActive)
                .roles(getRoleSet(isAdmin, isGm))
                .build();
    }

    public static UserDetailsImpl getUserDetailsAdmin() {
        return getUserDetails(UUID_1, "admin", true, false, true);
    }

    public static UserDetailsImpl getUserDetailsGm() {
        return getUserDetails(UUID_1, "user", false, true, true);
    }

    public static UserDetailsImpl getUserDetailsUser() {
        return getUserDetails(UUID_1, "user", false, false, true);
    }

    public static UserDetailsImpl getUserDetails(UUID id, String username, boolean isAdmin, boolean isGm, boolean isActive) {
        return UserDetailsImpl.build(getPlayerModel(id, username, isAdmin, isGm, isActive));
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

    public static List<UUID> getValidAptitudeIdList() {
        return Arrays.asList(UUID_1, UUID_2, UUID_3);
    }

    public static CreateSheetRequest getValidCreateSheetRequest() {
        return CreateSheetRequest.builder()
                .name("Valid")
                .lineageId(UUID_1)
                .backgroundId(UUID_2)
                .jobId(UUID_2)
                .aptitudeList(getValidAptitudeIdList())
                .intelligence(Dice.D4)
                .cunning(Dice.D4)
                .tenacity(Dice.D6)
                .celerity(Dice.D8)
                .build();
    }

    public static Stream<CreateSheetRequest> getInvalidCreateSheetRequestStream() {
        return Stream.of(
                CreateSheetRequest.builder().build(),
                CreateSheetRequest.builder()
                        .name("")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_2)
                        .jobId(UUID_2)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Test")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_2)
                        .jobId(UUID_2)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Test max characters!!")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_2)
                        .jobId(UUID_2)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_2)
                        .jobId(UUID_2)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .intelligence(Dice.D4)
                        .cunning(Dice.D4)
                        .celerity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .backgroundId(UUID_1)
                        .jobId(UUID_2)
                        .intelligence(Dice.D4)
                        .cunning(Dice.D4)
                        .celerity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_1)
                        .intelligence(Dice.D4)
                        .cunning(Dice.D4)
                        .celerity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_1)
                        .jobId(UUID_2)
                        .intelligence(Dice.D4)
                        .cunning(Dice.D4)
                        .tenacity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_1)
                        .tenacity(Dice.D4)
                        .cunning(Dice.D4)
                        .celerity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
                        .build(),
                CreateSheetRequest.builder()
                        .name("Valid")
                        .lineageId(UUID_1)
                        .backgroundId(UUID_1)
                        .intelligence(Dice.D4)
                        .tenacity(Dice.D4)
                        .celerity(Dice.D4)
                        .aptitudeList(getValidAptitudeIdList())
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
                .mentalCurrent(5)
                .mentalTotal(5)
                .physicalCurrent(9)
                .physicalTotal(9)
                .heroismCurrent(1)
                .heroismTotal(1)
                .player(getPlayerModel(playerId))
                .job(getJobModel())
                .build();
    }

    public static LineageModel getLineageModel() {
        return LineageModel.builder()
                .name("Anão")
                .build();
    }

    public static BackgroundModel getBackgroundModel() {
        return BackgroundModel.builder()
                .name("Fazendeiro")
                .physicalPoints(4)
                .mentalPoints(-1)
                .build();
    }

    public static Set<AptitudeModel> getAptitudeModelSet() {
        return Set.of(
                AptitudeModel.builder().id(UUID_1).build(),
                AptitudeModel.builder().id(UUID_2).build(),
                AptitudeModel.builder().id(UUID_3).build()
        );
    }

    public static JobModel getJobModel() {
        return JobModel.builder()
                .name("Artífice")
                .physicalPoints(5)
                .mentalPoints(8)
                .physicalPerLevel(4)
                .mentalPerLevel(2)
                .aptitudes(getAptitudeModelSet())
                .build();
    }

    public static BasicCampaignDto getBasicCampaignDto() {
        return BasicCampaignDto.builder()
                .id(UUID_1)
                .name("Test mesa")
                .build();
    }

    public static Page<BasicCampaignDto> getBasicCampaignDtoPage() {
        return new PageImpl<>(List.of(getBasicCampaignDto()));
    }

    public static IdListRequest getValidIdListRequest() {
        return IdListRequest.builder()
                .idList(List.of(UUID_1, UUID_2, UUID_3))
                .build();
    }

    public static CreateCampaignRequest getValidCreateCampaignRequest() {
        return CreateCampaignRequest.builder()
                .name("Test mesa")
                .notes("Some notes to test")
                .sheetList(getValidIdListRequest().getIdList())
                .build();
    }

    public static CampaignModel getCampaignModel() {
        return CampaignModel.builder()
                .id(UUID_1)
                .name("Test mesa")
                .sheets(Set.of(getSheetModel(UUID_2)))
                .build();
    }

    public static UpdateDeathRollsRequest getValidDeathRollsRequest() {
        return UpdateDeathRollsRequest.builder()
                .deathRollBody(DeathRollStatus.UNCHECKED)
                .deathRollMind(DeathRollStatus.SUCCESS)
                .deathRollSpirit(DeathRollStatus.FAILURE)
                .build();
    }

    public static Stream<UpdateDeathRollsRequest> getInvalidDeathRollsRequestStream() {
        return Stream.of(
                UpdateDeathRollsRequest.builder().build(),
                UpdateDeathRollsRequest.builder().deathRollBody(DeathRollStatus.UNCHECKED).build(),
                UpdateDeathRollsRequest.builder().deathRollMind(DeathRollStatus.UNCHECKED).build(),
                UpdateDeathRollsRequest.builder().deathRollSpirit(DeathRollStatus.UNCHECKED).build(),
                UpdateDeathRollsRequest.builder().deathRollBody(DeathRollStatus.UNCHECKED).deathRollMind(DeathRollStatus.SUCCESS).build(),
                UpdateDeathRollsRequest.builder().deathRollBody(DeathRollStatus.UNCHECKED).deathRollSpirit(DeathRollStatus.SUCCESS).build(),
                UpdateDeathRollsRequest.builder().deathRollMind(DeathRollStatus.UNCHECKED).deathRollSpirit(DeathRollStatus.SUCCESS).build()
        );
    }

    public static UpdateNotesRequest getNotesRequest(String note) {
        return UpdateNotesRequest.builder().notes(note).build();
    }
}
