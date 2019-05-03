package Objects;


import Controllers.ICrosser;

public class ObjectsFactory {

    public ICrosser getCrosser(String name) {
        if (name.equals("Fox")) {
            return new Fox();
        }
        if (name.equals("Goat")) {
            return new Goat();
        }
        if (name.equals("Donkey")) {
            return new Donkey();
        }
        if (name.equals("Lion")) {
            return new Lion();
        }
        if (name.equals("Cabbage")) {
            return new Cabbage();
        }
        if (name.equals("Carrot")) {
            return new Carrot();
        }
        if (name.equals("Farmer")) {
            return new Farmer();
        }
        return null;

    }
}