/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;


/**
 *
 * @author Rania
 */
public class TestFactory {
    public static void main(String[] args){
        ObjectsFactory f = new ObjectsFactory();
        
        Plant p1 = (Plant)f.getPlant();
        ICrosser p2 = (Plant)p1.makeCopy();
        
        System.out.println("carrot: "+p1);
        System.out.println("carrot2: "+p2);
        
    }
}
