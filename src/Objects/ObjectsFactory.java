package Objects;

import java.util.Random;

public class ObjectsFactory {
    
     Random rand = new Random();
    public ICrosser getSailor(){
        return new Farmer();
        
    }

    public ICrosser getCarnivore() {
        int x = rand.nextInt(2);
        
        switch(x){
            case 0:
                return new Fox();
            case 1:
                return new Lion();
            default:
                return null;
        }
        
    }
    
    public ICrosser getHerbivore(){
        
        int x = rand.nextInt(2);
        
        switch(x){
            case 0:
                return new Goat();
            case 1:
                return new Donkey();
            default:
                return null;
        }
    }
    
    public ICrosser getPlant(){
        int x = rand.nextInt(2);
        
        switch(x){
            case 0:
                return new Cabbage();
            case 1:
                return new Carrot();
            default:
                return null;
        }
    }
}