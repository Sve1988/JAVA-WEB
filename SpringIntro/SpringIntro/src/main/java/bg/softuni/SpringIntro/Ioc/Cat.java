package bg.softuni.SpringIntro.Ioc;

public class Cat implements Animal{
    @Override
    public void makeNoise() {
        System.out.println("Meow");
    }
}
