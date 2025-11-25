package com.example.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("database")
public class DatabaseFileService implements IFileService {
    private static Logger logger = LoggerFactory
        .getLogger(DatabaseFileService.class.getName());

    @Override
    public String upload(String id, String contentType, byte[] conteudo) {
        logger.info("\n>> arquivo chegou\n");
        return null;
    }
        
}
