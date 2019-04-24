
public class Factory {

    public ICrosser getCrosser(String name) {
        if (name.equals("Fox")) {
            return new Fox();
        }
        if (name.equals("Goat")) {
            return new Goat();
        }

        return null;

    }
}
