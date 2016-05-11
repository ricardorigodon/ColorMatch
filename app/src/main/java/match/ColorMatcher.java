package match;


import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import ricardo.colormatch.activities.MainActivity;
import ricardo.colormatch.data.ColorItem;
import ricardo.colormatch.data.ColorItems;

/**
 * This class will be used to process the colors that the user chose and return a boolean that will
 * give the result if the colors are a match or not.
 *
 * Uses hashmap to store values from matches file, deciphers colors by HSV, and processes
 * if the colors match or not and return to user.
 *
 *
 * Added for final takeHome : combos method that creates an ArrayList and creates all possible
 * permutations of the string given stores in list and that is passed into .containsValueOf for the
 * hashMap instead of the one string. Now matches.txt will not have to have multiple strings of the
 * same match in order for the value to have the correct value returned.
 *
 * @author Ricardo Rigodon
 */


public class ColorMatcher {


    //boolean value that will be used to return that will determine if color is a match or not.
    //initially set to false.
    private static boolean match = false;

    //red
    private final static int LRHUE = 0; //low range of hue
    private final static int HRHUE = 80; // high range of hue
    private final static int LRVA = 52; // low range of value
    private final static int HRVA = 100; // high range of value
    private final static int RSAT = 75; // saturation value

    //blue
    private final static int LBHUE = 200; // low range of hue
    private final static int HBHUE = 350; // high range of hue
    private final static int LBVA = 35; // low range of value
    private final static int HBVA = 75; // high range of value
    private final static int BSAT = 100; // saturation value

    //black
    private final static int LBLHUE = 0; // low range of hue
    private final static int HBLHUE = 95; // high range of hue
    private final static int BLVA = 20; // value
    private final static int BLSAT = 100; // saturation

    //white
    private final static int LWHUE = 195; // low range of white hue
    private final static int HWHUE = 350; // high range of hue
    private final static int LWVA = 64; // low range of value
    private final static int HWVA = 72; // high range of value
    private final static int WSAT = 8; // saturation value

    //green
    private final static int LGHUE = 60; // low range of hue
    private final static int HGHUE = 180; // high range of hue
    private final static int LGVA = 20; // low range of value
    private final static int HGVA = 100; // high range of value
    private final static int GSAT = 96; // saturation value

    //yellow
    private final static int LYHUE = 35; // low range of hue
    private final static int HYHUE = 75; // high range of hue
    private final static int LYVA = 70; // low range of value
    private final static int HYVA = 100; // high range of value
    private final static int LYSAT = 30; // low range of sat
    private final static int HYSAT = 100; // high range of sat

    //magenta
    private final static int LMHUE = 325; //low range of hue
    private final static int HMHUE = 500; // high range of hue
    private final static int LMVA = 60; // low range of value
    private final static int HMVA = 80; // high range of value
    private final static int LMSAT = 50; // low range of saturation
    private final static int HMSAT = 100; // high range of saturation

    //cyan
    private static final int LCHUE = 150; //low range of hue
    private static final int HCHUE = 200; //high range of hue
    private final static int LCVA = 52; // low range of value
    private final static int HCVA = 115; // high range of value
    private final static int CSAT = 100; // saturation value

    private static String yourColors = ""; //string of colors

    //Creates a hashmap with integer as key and strings as the value.
    private static Map<Integer, String> map = new HashMap<Integer, String>();


    /**
        Takes into the context where it is called and the list of savedColorItems that the user
        entered.

        @param context The context from which the method was called.
        @param list The list of saved color items that the user currently chose
     **/
    public static boolean isMatch(Context context, List<ColorItem> list) {

        AssetManager assetManager = context.getAssets(); //get assets from context
        BufferedReader reader = null; //create BufferedReader
        //match to false here in case it never reset
        match = false;

        try {
            //create new buffered reader and open input stream in matches.txt
            reader = new BufferedReader(new InputStreamReader(assetManager.open("matches.txt")));

            String mLine = ""; //line

            while ((mLine = reader.readLine()).length() > 1) {

                String parts[] = mLine.split("="); //split values on =
                parts[0] = parts[0].trim(); // remove whitespace
                int m = Integer.parseInt(parts[0]); // parseInt
                map.put(m, parts[1].trim()); // put in hashMap
            }
            reader.close(); // close reader


        } catch (IOException e) {

            e.printStackTrace(); //print trace of stack
        }


        //Grab the size of the list of color items and store that
        int count = list.size(); //grab size of list
        ColorItem[] colors = new ColorItem[count]; // create array to hold color item
        String[] hsvparts = new String[count]; //array to hold split of hsv parts
        Integer[] hue = new Integer[count]; //holds hue values
        Integer[] sat = new Integer[count];//holds saturation value
        Integer[] val = new Integer[count];//holds value from hsv
        String[] myColors = new String[count]; //array that holds

        for (int i = 0; i < list.size(); i++) {

            colors[i] = list.get(i); //get the ith item from the list
            String hsv = colors[i].getHsvString(); //get the HSV string
            String h = hsv.replaceAll("[^0-9.,]+", ""); // remove everything except hsv values


            hsvparts = h.split(","); //split array by comma to separate values


            //Convert values from string to int
            int hu = Integer.parseInt(hsvparts[0]); //hue
            int sa = Integer.parseInt(hsvparts[1]); // saturation
            int va = Integer.parseInt(hsvparts[2]); // value

            //Store the HSV values in the corresponding arrays
            hue[i] = hu; //H of HSV
            sat[i] = sa; // S of HSV
            val[i] = va; // V of HSV

            /*
             * The colors will be determined by analyzing the hue, saturation,
             * and value of each color item.
             */

            //red

            if ((hu >= LRHUE && hu <= HRHUE) && (va >= LRVA && va <= HRVA) && (sa >= RSAT)) {

                myColors[i] = "r"; //set to string of r (red)

            }

            //yellow

            else if ((hu >= LYHUE && hu <= HYHUE) && (va >= LYVA && va <= HYVA) && (sa >= LYSAT && sa <= HYSAT)) {

                myColors[i] = "y"; //set to string of y (yellow)

            }


            //green

            else if ((hu >= LGHUE && hu <= HGHUE) && (va >= LGVA && va <= HGVA) && (sa <= GSAT)) {

                myColors[i] = "g"; //set to string of g (green)

            }


            //cyan

            else if ((hu >= LCHUE && hu <= HCHUE) && (va >= LCVA && va <= HCVA) && (sa <= CSAT)) {

                myColors[i] = "c"; // set to string of c (cyan)

            }


            //blue

            else if ((hu >= LBHUE && hu <= HBHUE) && (va >= LBVA && va <= HBVA) && (sa <= BSAT)) {

                myColors[i] = "b"; //set to string of b (blue)

            }


            //magenta

            else if ((hu >= LMHUE && hu < HMHUE) && (va >= LMVA && va < HMVA) && (sa > LMSAT && sa <= HMSAT)) {

                myColors[i] = "m"; //set to string of m (magenta)

            }


            //white

            else if ((hu >= LWHUE && hu < HWHUE) && (va >= LWVA && va < HWVA) && (sa <= WSAT)) {

                myColors[i] = "w"; // set to string of w (white)

            }


            //black

            else if ((hu >= LBLHUE && hu < HBLHUE) && (va < BLVA) && (sa <= BLSAT)) {

                myColors[i] = "bl"; //set to string of bl (black)

            }


            //Assume the color is white having trouble of camera being able to identify it.
            // Avoids issue of null color for now.
            else if (myColors[i] == null) {
                myColors[i] = "w"; // set to string of w (white)
            }


        }


        for (int i = 0; i < myColors.length; i++) {

            yourColors += myColors[i]; //add to yourColors string

        }

        //store returned arrayList in stringCombos variable [takeHome]
        ArrayList<String> stringCombos = combos(yourColors);

        //boolean value that is set true if arraylist contains a value that is in hashmap loaded
        //by matches.txt [takeHome]
        boolean check = false;

        //check if map has any permutations of color string inside the hashmap of matches.
        //added to make more efficient than adding more lines in text file. [takeHome]
        for(int i = 0; i < stringCombos.size(); i++){

            if(map.containsValue(String.valueOf(stringCombos.get(i)))){
                //check to make sure only strings of length of list are checked against hashMap
                if(stringCombos.get(i).length() == count)
                check = true;
            }
        }

        //if it is in the hashMap it is a match
        if(check){
            match = true;
        }


        //reset color string
        yourColors = "";

        //clear ArrayList
        stringCombos.clear();


        //return match value
        return match;


    }

    /**
       *  Combos is a method that returns an ArrayList<String> of all the possible combinations
       *  of the characters into a string inside it. More efficient than
       * originally writing all possible permutations in matches.txt file. Added for Take Home Final.
       * Perk of ArrayList, more modular as more colors are added don't have to worry about resizing.
       *  @param  colors   String of colors ex. "wcbl"
     **/

    public static ArrayList<String> combos(String colors) {

        //ArrayList to hold all possible permutations of string
        ArrayList<String> combos = new ArrayList<String>();
        // will store length of arrayList as it is changing.
        int length;

        for(int i = 0; i < colors.length(); i++){

            //grab size of list as it changes
            length = combos.size();

            for(int j = 0; j < length; j++){
                //add list element and appended the list element at index j to it
                combos.add(colors.charAt(i) + combos.get(j));
            }
            //adds charAt index i to the array list
            combos.add(Character.toString(colors.charAt(i)));

        }

        //return arrayList of all the strings
        return combos;


    }

}


