package com.jetbrains.marco.photoz.clone.web;

import com.jetbrains.marco.photoz.clone.model.Photo;
import com.jetbrains.marco.photoz.clone.service.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class DownloadController {

    @Autowired
    private PhotozService photozService;

//    public DownloadController(PhotozService photozService) {
//        this.photozService = photozService;
//    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable String id) throws IOException {
        Photo photo = photozService.getPhoto(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }

        byte[] data = photo.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getContentType()));
//        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
