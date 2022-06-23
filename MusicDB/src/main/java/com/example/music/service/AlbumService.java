package com.example.music.service;

import com.example.music.model.entity.Album;
import com.example.music.model.entity.GenreEnum;
import com.example.music.model.service.AlbumServiceModel;
import com.example.music.model.service.UserServiceModel;
import com.example.music.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    void addAlbum(AlbumServiceModel serviceModel, UserServiceModel currentUser);

    Album findByAlbumGenreName(GenreEnum genre);

    List<AlbumViewModel> findAllAlbumView();

    Integer sumCopies();

    void deleteAlbum(Long id);
}
