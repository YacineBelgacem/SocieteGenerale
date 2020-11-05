/**
 *
 * @author yacinebelgacem
 */

package yacine_belgacem_sginterview;
import java.util.List;
import java.util.ArrayList;


public class Animation {

    static List<String> animate(int speed, String init){
        
        /*
        *Initialization of Rchamber and Lchamber, respectively Lists
        *containing RParticles and LParticles. tmp is the array which
        *will used for sizing the objects and distribute the data
        *in the lists.
        *result is the output variable
        *empty becomes true when Lchamber and Rchamber are both empty
        */
        char[] tmp = init.toCharArray();
        List<String> Rchamber = new ArrayList<String>(tmp.length);
        List<String> Lchamber = new ArrayList<String>(tmp.length);
        List<String> result = new ArrayList<String>(tmp.length);
        boolean empty = false;
        init = "";
         
        /*
        *Separating Lparticles and Rparticles
        *turning init into the required format to push into result
        */
        for (char item : tmp){
            if (Lchamber.size()<tmp.length){
                switch (item){
                    case '.' :
                        Rchamber.add (".");
                        Lchamber.add (".");
                        init += ".";
                        break;
                    case 'R' :
                        Rchamber.add ("R");
                        Lchamber.add (".");
                        init += "X";
                        break;
                    case 'L' :
                        Rchamber.add (".");
                        Lchamber.add ("L");
                        init += "X";
                        break;
                    default :
                        System.out.println("Invalid Input");
                        return result;
                } 
            }       
        }
        
        result.add(init);
        /*
        *Starting the loop where particles will be moved
        *As long as there will still be particles in the chamber,
        *the last for loop will set empty as false before starting
        *the while loop again.
        */
        while (!empty){
        empty = true;
        init = "";
            
            /*
            *Parsing and modifying Lchamber from left to right to not override data
            */
            for (int iterator = 0; iterator < tmp.length; iterator++){
                
                if (Lchamber.get(iterator) == "L"){
                    Lchamber.set(iterator, ".");
                    if (iterator - speed >= 0){
                        Lchamber.set(iterator - speed, "L");
                    }
                }
            }
            
            /*
            *Parsing and modifying Rchamber from right to left to not override data
            */
            for (int iterator = Rchamber.size()-1; iterator >= 0; iterator--){
                if (Rchamber.get(iterator) == "R"){
                    Rchamber.set(iterator, ".");
                    if (iterator + speed < Rchamber.size()){
                        Rchamber.set(iterator + speed, "R");
                    }
                }
            }
            
            /*
            *concatenate values into init according to the required format
            */
            for (int iterator = 0; iterator<tmp.length; iterator++){
                if (Lchamber.get(iterator)!="."|| Rchamber.get(iterator)!="." ){
                    init+="X";
                    empty = false;
                }else{
                    init+=".";
                }
            }
            /*
            *push init to result
            */
            result.add(init);
            
            
        }
        return result;
    }
}

