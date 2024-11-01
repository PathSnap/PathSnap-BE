package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResDTO> getProfile(@PathVariable String userId) {
        UserResDTO response = userService.getProfile(userId);
        return ResponseEntity.ok(response);
    }
}
