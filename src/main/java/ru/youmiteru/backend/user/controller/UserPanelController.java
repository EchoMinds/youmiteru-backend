package ru.youmiteru.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.user.service.UserPanelService;

@RestController
@RequestMapping("/api/user")
public class UserPanelController {
    private UserPanelService userPanelService;

    @Autowired
    public UserPanelController(UserPanelService userPanelService) {
        this.userPanelService = userPanelService;
    }

    @PutMapping("/{id}/settings")
    public User putUserName(@PathVariable Long id, @RequestBody String name){
        return userPanelService.updateUserName(id, name);
    }


}
