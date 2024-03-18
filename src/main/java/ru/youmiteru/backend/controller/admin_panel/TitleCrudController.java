package ru.youmiteru.backend.controller.admin_panel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.convertors.TitleConvertors;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.Title.TitleAdminPanelDto;
import ru.youmiteru.backend.dto.Title.TitlePageDTO;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.service.TitleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/titles")
public class TitleCrudController {
    private final TitleRepository titleRepository;
    private final TitleConvertors titleConvertors;
    private final TitleService titleService;
    public TitleCrudController(TitleRepository titleRepository, TitleConvertors titleConvertors, TitleService titleService) {
        this.titleRepository = titleRepository;
        this.titleConvertors = titleConvertors;
        this.titleService = titleService;
    }

    @GetMapping("/")
    public List<TitleAdminPanelDto> getAllSeasons() {
        return titleService.getAllSeasonsService();
    }

    @GetMapping("/{id}")
    private TitlePageDTO getTitlePage(@PathVariable Long id){
        return titleService.getTitlePage(id);
    }


    @PostMapping("/")
    public Title createTitle(@RequestBody Title title) {
        return titleRepository.save(title);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Title> updateTitle(@PathVariable Long id, @RequestBody Title title) {
        return titleService.updateTitle(id, title);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitleById(@PathVariable Long id) {
        titleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
