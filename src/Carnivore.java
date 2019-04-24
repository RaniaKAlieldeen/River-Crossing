
import java.awt.image.BufferedImage;

public abstract class Carnivore implements ICrosser {

    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public int getEatingRank() {
        return 5;
    }

    @Override
    public abstract BufferedImage[] getImages();

}
