package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.model.BackgroundModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundListResponse {
    private List<BackgroundModel> list = new ArrayList<>();
}
