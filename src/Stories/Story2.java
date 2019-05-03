package Stories;

import Objects.*;
import Objects.ICrosser;
import java.util.ArrayList;
import java.util.List;

public class Story2 implements IcrossingStrategy {

    List<ICrosser> c = new ArrayList<>();
    ObjectsFactory f = new ObjectsFactory();

    //Objects
    private List<ICrosser> rightBankCrosser = new ArrayList<>();
    private List<ICrosser> leftBankCrosser = new ArrayList<>();
    private List<ICrosser> onBoatCrosser = new ArrayList<>();

    public List<ICrosser> getRightBankCrosser() {
        return rightBankCrosser;
    }

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

    public Story2() {
        ICrosser farmer1 = f.getSailor();
        farmer1.setWeight(90);
        ICrosser farmer2 = f.getSailor();
        farmer2.setWeight(80);
        ICrosser farmer3 = f.getSailor();
        farmer3.setWeight(60);
        ICrosser farmer4 = f.getSailor();
        farmer4.setWeight(40);
        ICrosser carnivore = f.getCarnivore();
        carnivore.setWeight(20);

        c.add(farmer1);
        c.add(farmer2);
        c.add(farmer3);
        c.add(farmer4);
        c.add(carnivore);
        
        leftBankCrosser.add(farmer1);
        leftBankCrosser.add(farmer2);
        leftBankCrosser.add(farmer3);
        leftBankCrosser.add(farmer4);
        leftBankCrosser.add(carnivore);
        rightBankCrosser.clear();
        onBoatCrosser.clear();
    }

    @Override
    public boolean isValid(List<ICrosser> right, List<ICrosser> left, List<ICrosser> boatRiders) {
        double weights = 0;

        if (boatRiders.size() > 5) {
            return false;
        }

        for (int i = 0; i < boatRiders.size(); i++) {
            weights += boatRiders.get(i).getWeight();
            if (weights > 100) {
                return false;
            }
        }

        for (int i = 0; i < boatRiders.size(); i++) {
            if (boatRiders.get(i).canSail()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {

        return c;
    }

    @Override
    public String[] getInstructions() {
        String[] instructions = new String[100];
        instructions[0] = "The farmer is the only one who can sail the boat/n";
        instructions[1] = "The farmer can take only one passeneger in the boat/n";
        instructions[2] = "you cannt leave any two crpsser who can harm each other/n";
        instructions[3] = "carnevour can  harm a herbevour when left without a farmer.. /n";
        instructions[3] = "herbevour can  harm a Plant when left without a farmer.. /n";
        instructions[3] = "no body can harm a farmer.. /n";
        return instructions;
    }

    @Override
    public int getType() {
        return 2;
    }

}