package ru.youmiteru.backend.dto.Title;

import java.util.List;

public record TitlePageCountDto (
    Integer currentPage,
    Integer totalPage,
    List<TitleCatalogDTO> titlesForCatalog

){
    public Integer currentPage() {
        return currentPage;
    }

    public Integer totalPage() {
        return totalPage;
    }

    public List<TitleCatalogDTO> titlesForCatalog() {
        return titlesForCatalog;
    }

}

