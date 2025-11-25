package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IFileService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/evidences")
public class EvidencesController {
    private static Logger logger = LoggerFactory
        .getLogger(EvidencesController.class.getName());

    private final IFileService service;

    public EvidencesController(IFileService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestPart("evidence") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "arquivo nao veio"));
        }

        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String contentType = file.getContentType();

        try {
            this.service.upload(fileName, contentType, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("\n>> RECEBIDO ARQUIVO: {}, tamanho: {}, tipo: {}\n", fileName, fileSize, contentType);
                                                 
        return ResponseEntity.ok(Map.of("nome", fileName, "tipo", contentType, "tamanho", String.valueOf(fileSize)));
    }
    
}
