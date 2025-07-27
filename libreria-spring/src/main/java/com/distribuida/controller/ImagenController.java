package com.distribuida.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ImagenController {

    private static final String UPLOAD_DIR="uploads/portadas/";
    @PostMapping("/upload-portada")
    public ResponseEntity<Map<String, String>> uploadPortada(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value="oldImage", required = false) String oldImage
    ) {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.write(path, file.getBytes());

            if (oldImage != null && !oldImage.isEmpty()) {
                Path oldImagePath = Paths.get(oldImage);
                Files.deleteIfExists(oldImagePath);
            }

            Map<String, String> response = new HashMap<>();
            response.put("ruta", "portadas/" + filename);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("k1", "error", "v1", "Error al subir imagen" + e.getMessage()));
        }

    }
}

