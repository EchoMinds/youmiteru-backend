package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO.Response.TitleCatalogDTO;

@Component
@RequiredArgsConstructor
public class TitleConvertors {
    public TitleCatalogDTO convertToCatalogDTO(Title title){
        TitleCatalogDTO dto = new TitleCatalogDTO();

        dto.setTitleName(title.getName());
        dto.setTitleImageUrl(title.getTitleImageUrl());

        return dto;
    }
}
