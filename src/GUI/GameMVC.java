package GUI;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rania
 */
public class GameMVC {

    public static void main(String[] args) {
        GameController engine;
        //GameModel theModel = new GameModel();
        //RCGUI v = RCGUI.getTheView();
        RCGUI.view();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
        //RCGUI v = RCGUI.getTheView();
       engine = GameController.createSingleton();

    }

}
