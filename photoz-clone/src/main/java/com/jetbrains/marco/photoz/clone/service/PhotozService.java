package com.jetbrains.marco.photoz.clone.service;

import com.jetbrains.marco.photoz.clone.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotozService {
    private Map<String, Photo> db = new HashMap<String, Photo>() {{
        put("1", new Photo("1", "photo1.jpg"));
        put("2", new Photo("2", "photo2.jpg"));
    }};

    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo getPhoto(String id) {
        return db.get(id);
    }

    public Photo removePhoto(String id) {
        return db.remove(id);
    }

    public Photo createPhoto(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        db.put(photo.getId(), photo);
        return photo;
    }
}
