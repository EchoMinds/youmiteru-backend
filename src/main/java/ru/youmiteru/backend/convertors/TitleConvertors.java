package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;

@Component
@RequiredArgsConstructor
public class TitleConvertors {
    public TitleCatalogDTO convertToCatalogDTO(Title title){
        return new TitleCatalogDTO(
            title.getId(),
            title.getName(),
            title.getTitleImageUrl()
        );
    }
}
