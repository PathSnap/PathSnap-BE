package com.pathsnap.Backend.Record.Controller;

import com.pathsnap.Backend.Record.Service.RecordStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController //json 형식으로 반환
@RequestMapping("/records")
public class RecordStartController {

    @Autowired
    RecordStartService recordStartService;

    @GetMapping("/start/{userId}/{recordIsGroup}")
    public ResponseEntity<Map<String,String>> startRecord(@PathVariable String userId,@PathVariable boolean recordIsGroup) {
        String recordId = recordStartService.startNewRecord(userId,recordIsGroup);

        Map<String,String> response = new HashMap<>();
        response.put("recordId",recordId);

        return ResponseEntity.ok(response);
    }
}
