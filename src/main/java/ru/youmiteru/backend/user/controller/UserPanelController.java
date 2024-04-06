package ru.youmiteru.backend.user.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.user.service.UserPanelService;

@RestController
@Data
@RequestMapping("/api/user")
public class UserPanelController {
    private UserPanelService userPanelService;

    @PutMapping("/{id}/settings")
    public User putUserName(@PathVariable Long id, @RequestBody String name){
        return userPanelService.updateUserName(id, name);
    }


}
