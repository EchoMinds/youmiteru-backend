package ru.youmiteru.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpStatus putUserName(@PathVariable Long id, @RequestBody String name){
        User user = userPanelService.updateUserName(id, name);
        if(user != null){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }


}
