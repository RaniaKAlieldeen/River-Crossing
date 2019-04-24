
import java.util.List;



public interface IcrossingStrategy {

    public boolean isValid(List<ICrosser> right, List<ICrosser> left, List<ICrosser> boatRiders);

    public List<ICrosser> getInitialCrossers();

    public String[] getInstructions();
}
