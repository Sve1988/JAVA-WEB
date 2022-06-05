package bg.softuni.SpringIntro.Cats.service;

import bg.softuni.SpringIntro.Cats.model.DTO.CreateOwnerDto;

public interface OwnerService {

    void createOwner(CreateOwnerDto createOwnerDto);
}
