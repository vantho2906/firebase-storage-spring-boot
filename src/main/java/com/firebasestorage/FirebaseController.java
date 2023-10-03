package com.firebasestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("firebase")
public class FirebaseController {
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("create")
    public ResponseEntity create(@RequestParam(name = "file") MultipartFile file) {
        try {
            firebaseService.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Upload file success");
    }

    @GetMapping("get")
    public ResponseEntity<Map<String, String>> get(@RequestParam(name = "name") String name) {
        Map<String, String> map = new HashMap<>();
        String linkAccess = firebaseService.getLinkAccess(name);
        map.put("link", linkAccess);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
