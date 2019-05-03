package Stories;

import Objects.*;
import Controllers.ICrosser;
import Controllers.IcrossingStrategy;
import java.util.ArrayList;
import java.util.List;

public class Story1 implements IcrossingStrategy {

    List<ICrosser> c = new ArrayList<>();
    ObjectsFactory f = new ObjectsFactory();

    //Objects
    private List<ICrosser> rightBankCrosser = new ArrayList<>();
    private List<ICrosser> leftBankCrosser = new ArrayList<>();
    private List<ICrosser> onBoatCrosser = new ArrayList<>();

    @Override
    public List<ICrosser> getRightBankCrosser() {
        return rightBankCrosser;
    }

    @Override
    public List<ICrosser> getLeftBankCrosser() {
        return leftBankCrosser;
    }

    public List<ICrosser> getOnBoatCrosser() {
        return onBoatCrosser;
    }

    public void addRightBankCrosser(ICrosser rightBankCrosser) {
        this.rightBankCrosser.add(rightBankCrosser);
    }

    public void removeRightBankCrosser(ICrosser rightBankCrosser) {
        this.rightBankCrosser.remove(rightBankCrosser);
    }

    public void addLeftBankCrosser(ICrosser leftBankCrosser) {
        this.leftBankCrosser.add(leftBankCrosser);
    }

    public void removeLeftBankCrosser(ICrosser leftBankCrosser) {
        this.leftBankCrosser.remove(leftBankCrosser);
    }

    public void addonBoatCrosser(ICrosser onBoatCrosser) {
        this.onBoatCrosser.add(onBoatCrosser);
    }

    public void removeonBoatCrosser(ICrosser onBoatCrosser) {
        this.onBoatCrosser.remove(onBoatCrosser);
    }

    public Story1() {
        c.clear();
        ICrosser fox = f.getCrosser("Fox");
        ICrosser goat = f.getCrosser("Goat");
        ICrosser cabbage = f.getCrosser("Cabbage");
        ICrosser farmer = f.getCrosser("Farmer");
        c.add(fox);
        c.add(goat);
        c.add(cabbage);
        c.add(farmer);
        leftBankCrosser.add(fox);
        leftBankCrosser.add(goat);
        leftBankCrosser.add(cabbage);
        leftBankCrosser.add(farmer);
        rightBankCrosser.clear();
        onBoatCrosser.clear();
    }

    @Override
    public boolean isValid(List<ICrosser> right, List<ICrosser> left, List<ICrosser> boatRiders) {
        boolean valid = false;
        if (boatRiders.size() > 2) {
            return false;
        }

        if (right.size() == 2) {
            if (Math.abs(right.get(0).getEatingRank() - right.get(1).getEatingRank()) == 1) {
                return false;
            }
        }
        if (left.size() == 2) {
            if (Math.abs(left.get(0).getEatingRank() - left.get(1).getEatingRank()) == 1) {
                return false;
            }
        }

        if (right.size() == 3) {
            if (((Math.abs(right.get(0).getEatingRank() - right.get(1).getEatingRank()) == 1)
                    && right.get(2).getEatingRank() != 10)
                    || ((Math.abs(right.get(0).getEatingRank() - right.get(2).getEatingRank()) == 1)
                    && right.get(1).getEatingRank() != 10)
                    || ((Math.abs(right.get(1).getEatingRank() - right.get(2).getEatingRank()) == 1)
                    && right.get(0).getEatingRank() != 10)) {
                return false;
            }
        }

        if (left.size() == 3) {
            if (((Math.abs(left.get(0).getEatingRank() - left.get(1).getEatingRank()) == 1)
                    && left.get(2).getEatingRank() != 10)
                    || ((Math.abs(left.get(0).getEatingRank() - left.get(2).getEatingRank()) == 1)
                    && left.get(1).getEatingRank() != 10)
                    || ((Math.abs(left.get(1).getEatingRank() - left.get(2).getEatingRank()) == 1)
                    && left.get(0).getEatingRank() != 10)) {
                return false;
            }
        }
        
        for (int i = 0; i < boatRiders.size(); i++) {
            if (boatRiders.get(i).canSail()) {
                valid = true;
            }            
        }

        return valid;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        return c;
    }

    @Override
    public String[] getInstructions() {
        String[] instructions = new String[1];
        instructions[0] = "The farmer is the only one who can sail the boat\n"
                + "The farmer can take only one passeneger in the boat\n"
                + "you cannt leave any two crosser who can harm each other\n"
                + "Carnevour can  harm a herbevour when left without a farmer..\n"
                + "herbevour can  harm a Plant when left without a farmer.. \n"
                + "no body can harm a farmer.. \n";
        return instructions;
    }

    @Override
    public int getType() {
        return 1;
    }
}
