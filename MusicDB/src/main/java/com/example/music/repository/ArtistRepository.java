package com.example.music.repository;

import com.example.music.model.entity.Artist;
import com.example.music.model.entity.ArtistEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByName(ArtistEnum artist);
}
