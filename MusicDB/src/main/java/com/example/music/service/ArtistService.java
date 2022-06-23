package com.example.music.service;

import com.example.music.model.entity.Artist;
import com.example.music.model.entity.ArtistEnum;

public interface ArtistService {
    void initCategories();

    Artist findByArtistEnumName(ArtistEnum artist);

}
