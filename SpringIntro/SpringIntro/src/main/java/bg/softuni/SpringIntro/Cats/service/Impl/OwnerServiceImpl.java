package bg.softuni.SpringIntro.Cats.service.Impl;

import bg.softuni.SpringIntro.Cats.model.DTO.CreateOwnerDto;
import bg.softuni.SpringIntro.Cats.model.entity.CatEntity;
import bg.softuni.SpringIntro.Cats.model.entity.OwnerEntity;
import bg.softuni.SpringIntro.Cats.repository.OwnerRepository;
import bg.softuni.SpringIntro.Cats.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void createOwner(CreateOwnerDto createOwnerDto) {

        OwnerEntity owner = new OwnerEntity().setOwnerName(createOwnerDto.getOwnerName());

        createOwnerDto.getCatNames().forEach(name -> {
            CatEntity cat = new CatEntity().setCatName(name).setOwner(owner);
            owner.addCat(cat);
        });

        ownerRepository.save(owner);

    }
}
