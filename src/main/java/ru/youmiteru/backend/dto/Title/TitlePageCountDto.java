package ru.youmiteru.backend.dto.Title;

import java.util.List;

public record TitlePageCountDto (
    Integer currentPage,
    Integer totalPage,
    List<TitleCatalogDTO>titlesForCatalog

){}

