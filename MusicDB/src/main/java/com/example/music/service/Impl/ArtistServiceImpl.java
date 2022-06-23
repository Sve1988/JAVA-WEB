package com.example.music.service.Impl;

import com.example.music.model.entity.Artist;
import com.example.music.model.entity.ArtistEnum;
import com.example.music.repository.ArtistRepository;
import com.example.music.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void initCategories() {
        if (artistRepository.count() == 0) {
            Arrays.stream(ArtistEnum.values())
                    .forEach(artistEnum -> {
                        Artist artist = new Artist();
                        artist.setName(artistEnum);
                        artist.setCareerInformation("Description for " + artistEnum.name().toLowerCase());
                        artistRepository.save(artist);
                    });
        }
    }

    @Override
    public Artist findByArtistEnumName(ArtistEnum artist) {
        return artistRepository.findByName(artist).orElse(null);
    }
}
