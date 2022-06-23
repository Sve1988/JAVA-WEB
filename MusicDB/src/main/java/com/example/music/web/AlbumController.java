package com.example.music.web;

import com.example.music.model.binding.AlbumAddBindingModel;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Artist;
import com.example.music.model.entity.GenreEnum;
import com.example.music.model.service.AlbumServiceModel;
import com.example.music.model.service.UserServiceModel;
import com.example.music.service.AlbumService;
import com.example.music.service.ArtistService;
import com.example.music.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public AlbumController(AlbumService albumService, ArtistService artistService, UserService userService, ModelMapper modelMapper, HttpSession httpSession) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String addOrder() {
        return "add-album";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid AlbumAddBindingModel albumAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        AlbumServiceModel serviceModel = modelMapper
                .map(albumAddBindingModel, AlbumServiceModel.class);

        Artist artist = artistService
               .findByArtistEnumName(albumAddBindingModel.getArtist());
        serviceModel.setArtist(artist);

        UserServiceModel currentUser = modelMapper
                .map(httpSession
                        .getAttribute("user"), UserServiceModel.class);

        albumService.addAlbum(serviceModel, userService
                .findByUsername(currentUser.getUsername()));

        return "redirect:/";
    }

    @ModelAttribute
    public AlbumAddBindingModel albumAddBindingModel() {
        return new AlbumAddBindingModel();
    }
}
