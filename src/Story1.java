
import java.util.ArrayList;
import java.util.List;



public class Story1 implements IcrossingStrategy {

    @Override
    public boolean isValid(List<ICrosser> right, List<ICrosser> left, List<ICrosser> boatRiders) {
        boolean valid = false;
        for (int i = 0; i < boatRiders.size(); i++) {
            ICrosser x = boatRiders.get(i);
            if (x.canSail()) {
                valid = true;
                break;
            }
        }
        if (valid == false) {
            return false;
        }
        if (left.size() == 3) {
            return false;
        } else if (left.size() == 2) {
            int diff=left.get(0).getEatingRank()-left.get(1).getEatingRank();
        if(diff==1 ||diff==-1)
            return false;
        }
        return true;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        List<ICrosser> c = new ArrayList<>();
        Factory f = new Factory();
        c.add(new Farmer());
        c.add(new Plant());
        c.add(f.getCrosser("Fox"));
        c.add(f.getCrosser("Goat"));

        return c;
    }

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
