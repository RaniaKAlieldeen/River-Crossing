
public class CarnivoreFactory extends AbstractFactory {

    @Override
    public Carnivore getCarnivoire(String cname) {
        if (cname.equals("Fox")) {
            return new Fox();
        }
        return null;
    }

    @Override
    public Harbivore getHarbivoire(String hname) {
        return null;
    }



}
