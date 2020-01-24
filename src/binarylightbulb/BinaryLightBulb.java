/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarylightbulb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author le_thom
 */
class FindDefective extends Thread
{
    private int pivot;
    private int[] intArray;
    private List<Integer> posOfLightOff = new ArrayList<Integer>();
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
            if (lightoff(intArray)) { //if contains 0
                if (intArray.length == 1)
                {
                    posOfLightOff.add(1);
                }
                else {
                    int[] array1 = Arrays.copyOfRange(intArray, 0, pivot + 1);
                    int[] array2 = Arrays.copyOfRange(intArray, pivot + 1, intArray.length);
                    FindDefective obj1 = new FindDefective(array1); //first half of split
                    FindDefective obj2 = new FindDefective(array2); //second half of split

                    obj1.start();
                    obj2.start();
                    obj1.join();
                    System.out.println(obj1.getName() + "is done");
                    obj2.join();
                    System.out.println(obj2.getName() + "is done");

                    List<Integer> list1 = obj1.getPosOfLightOff();
                    List<Integer> list2 = obj2.getPosOfLightOff();
                    if (list1 != null)
                        for (int item: list1)
                            posOfLightOff.add(item);

                    if (list2 != null)
                        for (int item: list2)
                            posOfLightOff.add(item + pivot + 1);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
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

    public List<Integer> getPosOfLightOff()
    {
        return posOfLightOff;
    }
}
public class BinaryLightBulb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[] intArray = {6,1,0,1,1,0,1,1,1};
       intArray = Arrays.copyOfRange(intArray, 1, (intArray.length));
       FindDefective obj = new FindDefective(intArray);
       obj.start();
        try {
            obj.join();
            List<Integer> listObj = obj.getPosOfLightOff();
            if (listObj == null)
                System.out.println("There are no burnt light bulbs");
            else
                for(int item: listObj)
                    System.out.println ("The light bulb is off at : " + (item));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

