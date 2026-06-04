package com.jetbrains.marco.photoz.clone.web;

import com.jetbrains.marco.photoz.clone.model.Photo;
import com.jetbrains.marco.photoz.clone.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {
    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {
        return photozService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id) {
        Photo photo = photozService.getPhoto(id);

        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }

        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable String id) {
        Photo photo = photozService.removePhoto(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }

    @PostMapping("/photos")
    public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.createPhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
