/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarylightbulb;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author le_thom
 */
class FindDefective extends Thread
{
    int pivot;
    int[] intArray;
    int[] posOfLightOff;
    FindDefective(int[] intArray)
    {
        super();
        pivot = (intArray.length - 1)/2;       
        this.intArray = intArray;
        System.out.println("my thread created " + this);
        //start();
    }
     
    public void run()
    {
        try
        { //should do something when length is 1 or more
            if (lightoff(intArray)) {
                if (intArray.length > 2) {
                    int[] array1 = Arrays.copyOfRange(intArray, 0, pivot + 1);
                    int[] array2 = Arrays.copyOfRange(intArray, pivot + 1, intArray.length);
                    FindDefective obj1 = new FindDefective(array1); //first half of split
                    FindDefective obj2 = new FindDefective(array2); //second half of split
                }
                else {
                    //do something if length == 1
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("error caught");
        }
    }
    //Check if the array contains a 0.
    private boolean lightoff (int[] intArray)
    {
        for (int item : intArray)
            if (item == 0)
                return true;
        return false;
    }
}
public class BinaryLightBulb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[] intArray = {6,0,0,1,1,0,1};
       intArray = Arrays.copyOfRange(intArray, 1, (intArray.length - 1));
       FindDefective obj = new FindDefective(intArray);
       obj.start();
        
    }
}

