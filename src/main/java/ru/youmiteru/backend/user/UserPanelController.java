package ru.youmiteru.backend.user;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.User;

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
