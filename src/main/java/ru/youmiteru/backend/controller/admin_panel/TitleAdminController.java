package ru.youmiteru.backend.controller.admin_panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.convertors.TitleConvertors;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.Title.TitleAdminPanelDto;
import ru.youmiteru.backend.dto.Title.TitlePageDTO;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/title")
public class TitleAdminController {
    private final TitleRepository titleRepository;
    private final TitleService titleService;

    @Autowired
    public TitleAdminController(TitleRepository titleRepository, TitleService titleService) {
        this.titleRepository = titleRepository;
        this.titleService = titleService;
    }

    @GetMapping
    public List<TitleAdminPanelDto> getAllSeasons() {
        return titleService.getAllSeasonsService();
    }

    @GetMapping("/{id}")
    private TitlePageDTO getTitlePage(@PathVariable Long id){
        return titleService.getTitlePage(id);
    }


    @PostMapping
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
