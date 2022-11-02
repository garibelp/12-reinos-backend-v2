package br.com.extratora.twelvekingdoms;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import br.com.extratora.twelvekingdoms.enums.RolesEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.model.RoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TestPayloads {

    public static Page<BasicPlayerDto> getPlayerDtoPage() {
        return new PageImpl<>(List.of(getBasicPlayerDto()));
    }

    public static BasicPlayerDto getBasicPlayerDto() {
        return BasicPlayerDto.builder()
                .id(UUID.randomUUID())
                .username("username")
                .email("user@mail.com")
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

    public static PlayerModel getPlayerModel(String username, boolean isAdmin) {
        return PlayerModel.builder()
                .username(username)
                .roles(getRoleSet(isAdmin))
                .build();
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
}
