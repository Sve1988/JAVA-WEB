package bg.softuni.SpringIntro.Cats;

import bg.softuni.SpringIntro.Cats.model.DTO.CreateOwnerDto;
import bg.softuni.SpringIntro.Cats.service.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CatDemo implements CommandLineRunner {

    private OwnerService ownerService;

    public CatDemo(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
     //   List<String> catNames = new ArrayList<>();
        System.out.println("Enter the number of owners");
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < number; i++) {
            List<String> catNames = new ArrayList<>();
            System.out.println("Please enter owner name");
            String ownerName = scanner.nextLine();
            for (int j = 0; j < 3; j++) {
            System.out.println("Enter cat name");
            String catName = scanner.nextLine();
            catNames.add(catName);
        }
        ownerService.createOwner(new CreateOwnerDto().setOwnerName(ownerName).
                setCatNames(catNames));
        }
      //  System.out.println("Please enter owner name");
      //  String ownerName = scanner.nextLine();
        /*for (int i = 0; i < 3; i++) {
            System.out.println("Enter cat name");
            String catName = scanner.nextLine();
            catNames.add(catName);
        }
        ownerService.createOwner(new CreateOwnerDto().setOwnerName(ownerName).
                setCatNames(catNames));*/
    }


}
