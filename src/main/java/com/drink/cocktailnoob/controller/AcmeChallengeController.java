package com.drink.cocktailnoob.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class AcmeChallengeController {

    private static final String CHALLENGE_DIR = "/Users/sihoonyoo/IdeaProjects/cocktailnoob/src/main/resources/.well-known/acme-challenge/";

    @GetMapping("/.well-known/acme-challenge/{filename}")
    public ResponseEntity<FileSystemResource> getChallengeFile(@PathVariable("filename") String filename) {
        File file = new File(CHALLENGE_DIR + filename);
        if (file.exists()) {
            return ResponseEntity.ok(new FileSystemResource(file));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}