package com.example.music.service.Impl;

import com.example.music.model.entity.Album;
import com.example.music.model.entity.GenreEnum;
import com.example.music.model.entity.User;
import com.example.music.model.service.AlbumServiceModel;
import com.example.music.model.service.UserServiceModel;
import com.example.music.model.view.AlbumViewModel;
import com.example.music.repository.AlbumRepository;
import com.example.music.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addAlbum(AlbumServiceModel serviceModel, UserServiceModel currentUser) {
        Album album = modelMapper.map(serviceModel, Album.class);
        album.setAddedFrom(modelMapper.map(currentUser, User.class));
        albumRepository.save(album);
    }

    @Override
    public Album findByAlbumGenreName(GenreEnum genre) {
        return albumRepository.findByGenre(genre).orElse(null);
    }

    @Override
    public List<AlbumViewModel> findAllAlbumView() {
        return albumRepository.findAll()
                .stream()
                .map(album -> {
                    AlbumViewModel viewModel = modelMapper.map(album, AlbumViewModel.class);
                    viewModel.setArtist(album.getArtist().getName().name());
                    viewModel.setGenre(album.getGenre().name());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Integer sumCopies() {
        return albumRepository.findAll()
                .stream()
                .mapToInt(Album::getCopies)
                .sum();
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }


}
