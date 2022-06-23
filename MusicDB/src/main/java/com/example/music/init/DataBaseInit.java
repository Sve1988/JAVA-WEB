package com.example.music.init;

import com.example.music.repository.ArtistRepository;
import com.example.music.service.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final ArtistService artistService;

    public DataBaseInit(ArtistRepository artistRepository, ArtistService artistService) {
        this.artistService = artistService;
    }


    @Override
    public void run(String... args) throws Exception {
        artistService.initCategories();
    }
}
