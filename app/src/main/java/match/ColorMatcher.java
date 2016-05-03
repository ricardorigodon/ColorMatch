package match;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ricardo.colormatch.activities.MainActivity;
import ricardo.colormatch.data.ColorItem;
import ricardo.colormatch.data.ColorItems;

/**
 * Created by Ricardo on 4/1/2016.
 * This class will be used to process the colors that the user chose and return a boolean that will
 * give the result if the colors are a match or not.
 */


public class ColorMatcher {


    //boolean value that will be used to return that will determine if color is a match or not.
    //initially set to false.
    private static boolean match = false;

    /*
        Takes into the context where it is called and the list of savedColorItems that the user
        entered.

        @param context The context from which the method was called.
        @param list The list of saved color items that the user currently chose
     */
    public static boolean isMatch(Context context, List<ColorItem> list) {


        //Grab the size of the list of color items and store that
        int count = list.size();

        list = ColorItems.getSavedColorItems(context); //grab list of saved colors
        ColorItem[] colors = new ColorItem[10]; //will hold color items
        String[] hsvparts = new String[count]; //will hold result of hsvString regex
        Integer[] hue = new Integer[count]; //will hold hue values
        Integer[] sat = new Integer[count]; // will hold saturation values
        Integer[] val = new Integer[count]; // will hold vibrance values
        String[] myColors = new String[count]; // how many colors are in the list


        for (int i = 0; i < list.size(); i++) {

            colors[i] = list.get(i); //grab the i element in the list
            String hsv = colors[i].getHsvString(); //get the HSV string
            String h = hsv.replaceAll("[^0-9.,]+", ""); // remove everything except hsv values

            // Log.d("TAG", "hsv reads : " + h);

            // String h example now reads 162, 2, 97

            hsvparts = h.split(","); //split array by comma to separate values


            /*
            Log.d("HSVParts", "hsv parts location reads : " +hsvparts[i]);
            Log.d("HSVParts", "hsv parts location reads : " +hsvparts[i+1]);
            Log.d("HSVParts", "hsv parts location reads : " +hsvparts[i+2]);
            */


            //Convert values from string to int
            int hu = Integer.parseInt(hsvparts[0]); //hue
            int sa = Integer.parseInt(hsvparts[1]); // saturation
            int va = Integer.parseInt(hsvparts[2]); // value

            //Store the values in the corresponding arrays
            hue[i] = hu;
            sat[i] = sa;
            val[i] = va;

            /*
              The colors will be determined by analyzing the hue, saturation,
               and value of each color item.

             */

            //red
            if ((hu >= 0 && hu <= 14) && (va >= 52 && va <= 100 ) && (sa > 60)) {

                myColors[i] = "red";

            }

            //yellow

            else if ((hu >= 40 && hu < 75) && (va >= 74 && va < 100) && (sa >= 20 && sa <= 70)) {

                myColors[i] = "yellow";

            }


            //green

             else if ((hu >= 70 && hu <= 180) && (va >= 10 && va <= 55) && (sa <= 100)) {

                myColors[i] = "green";

            }


            //cyan

            else if ((hu >= 150 && hu < 200) && (va >= 52 && va < 115) && (sa <= 100)) {

                myColors[i] = "cyan";

            }


            //blue

            else if ((hu >= 200 && hu < 350) && (va >= 50 && va < 75) && (sa < 100)) {

                myColors[i] = "blue";

            }


            //magenta

            else if ((hu >= 325 && hu < 500) && (va >= 60 && va < 80) && (sa > 50 && sa <= 100)) {

                myColors[i] = "magenta";

            }


            //white

           else if ((hu >= 195 && hu < 350) && (va >= 64 && va < 72) && (sa < 5)) {

                myColors[i] = "white";

            }


            //black

            else if ((hu >= 0 && hu < 60) && (va < 11) && (sa <= 100)) {

                myColors[i] = "black";

            }


            //Assume the color is white having trouble of camera being able to identify it.
            // In later iterations I will plan to add more colors and address this.
            //This can cause problems with match problem - when color is not in primaries.

           else if (myColors[i] == null) {
                myColors[i] = "white";
            }


            Log.d("Hue", "H of " + i + ": "+hu);
            Log.d("Sat", "S of " + i + ": "+sa);
            Log.d("V", "V of " + i + ": "+va);

            Log.d("Color", "Color detected: " +myColors[i]);



        }

        //pass into function to check if all the colors are the same value
        boolean allSame = AreAllSame(myColors);


        //We change it into a set because 1 its more efficient to check if a set contains a string
        // in it for matching purposes. and 2 because we can use .contains vs .equals for all the positions
        // in the array. Makes for cleaner code and a lot better than trying for every possibility
        // Ex. if(colors[0].equals .. colors[1].equals ..)

        final Set<String> yourColors = new HashSet<String>(Arrays.asList(myColors));

        //for 2 colors

        if(list.size() == 2){

            if (allSame && (yourColors.contains("red") || yourColors.contains("black") || yourColors.contains("white")
                    || yourColors.contains("blue") || yourColors.contains("yellow") || yourColors.contains("magenta")
                    || yourColors.contains("cyan")))
            {

                match = true;
            }

            if(yourColors.contains("black") && yourColors.contains("red")){

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("blue")){

                match = true;
            }

            if(yourColors.contains("green") && yourColors.contains("yellow")){

                match = true;
            }

            if(yourColors.contains("black")){

                match = true;
            }

            if(yourColors.contains("black") && yourColors.contains("white")){

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("green")){

                match = true;
            }

            if(yourColors.contains("white")){

                match = true;
            }

            if(yourColors.contains("cyan") && yourColors.contains("blue")){

                match = true;
            }

            if(yourColors.contains("magenta") && yourColors.contains("yellow")){

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("yellow")){

                match = true;
            }
        }


        //for 3 colors
        if(list.size() == 3) {

            //if colors are all the same. We add the second part besides allSame because if the list is null then
            // allSame will return true.
            if (allSame && (yourColors.contains("red") || yourColors.contains("black") || yourColors.contains("white")
                    || yourColors.contains("blue") || yourColors.contains("yellow") || yourColors.contains("magenta")
                    || yourColors.contains("cyan")))

                match = true;


            if (yourColors.contains("blue") && yourColors.contains("green") && (yourColors.contains("white")
                    || yourColors.contains("black") || yourColors.contains("green"))) {

                match = true;
            }

            if (yourColors.contains("red") && yourColors.contains("black") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("green") && yourColors.contains("black") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("black") && yourColors.contains("blue") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("blue") && yourColors.contains("black") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("blue") && yourColors.contains("black") && yourColors.contains("black")) {

                match = true;
            }

            if (yourColors.contains("yellow") && yourColors.contains("black") && yourColors.contains("red")) {

                match = true;
            }

            if (yourColors.contains("blue") && yourColors.contains("white") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("yellow") && yourColors.contains("blue") && (yourColors.contains("white")
                || yourColors.contains("black"))){

                match = true;
            }

            if (yourColors.contains("black") && yourColors.contains("white") && yourColors.contains("white")) {

                match = true;
            }

            if (yourColors.contains("white") && yourColors.contains("black") && yourColors.contains("black")) {

                match = true;
            }

            if (yourColors.contains("black") && yourColors.contains("cyan")) {

                match = true;

            }

            if (yourColors.contains("yellow") && yourColors.contains("magenta") && (yourColors.contains("black")
            || yourColors.contains("white"))){

                match = true;
            }
        }

        // Log for console for debugging
        Log.d("AllSame", "Truth value : "+allSame);



        //for 4 colors
        if(list.size() == 4) {

            if (allSame && (yourColors.contains("red") || yourColors.contains("black") || yourColors.contains("white")
                    || yourColors.contains("blue") || yourColors.contains("yellow") || yourColors.contains("magenta")
                    || yourColors.contains("cyan"))){

                Log.d("LIST", "Here : ");
                match = true;


            }

            if(yourColors.contains("yellow") && yourColors.contains("magenta") && (yourColors.contains("black")
                    || yourColors.contains("white"))){

                match = true;
            }

            if(yourColors.contains("red") && yourColors.contains("black") && yourColors.contains("white") &&
                    yourColors.contains("black")) {

                match = true;
            }

            if(yourColors.contains("green") && yourColors.contains("black") && yourColors.contains("white") &&
                    yourColors.contains("white")) {

                match = true;
            }

            if(yourColors.contains("black") && yourColors.contains("black") && yourColors.contains("blue") &&
                    yourColors.contains("white")) {

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("black") && yourColors.contains("white") &&
                    yourColors.contains("white")) {

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("black") && yourColors.contains("black")
                    && yourColors.contains("blue")) {

                match = true;
            }

            if(yourColors.contains("yellow") && yourColors.contains("black") && yourColors.contains("red") &&
                    yourColors.contains("yellow")) {

                match = true;
            }

            if(yourColors.contains("yellow") && yourColors.contains("black") && yourColors.contains("red") &&
                    yourColors.contains("red")) {

                match = true;
            }

            if(yourColors.contains("black") && yourColors.contains("black") && yourColors.contains("red") &&
                    yourColors.contains("red")) {

                match = true;
            }

            if(yourColors.contains("white") && yourColors.contains("black") && yourColors.contains("red") &&
                    yourColors.contains("red")) {

                match = true;
            }

            if(yourColors.contains("blue") && yourColors.contains("white") && yourColors.contains("white")
                    && yourColors.contains("blue")) {

                match = true;
            }

            if(yourColors.contains("black") && yourColors.contains("white") && yourColors.contains("white")
                    && yourColors.contains("yellow")) {

                match = true;
            }

            if(yourColors.contains("white") && yourColors.contains("black") && yourColors.contains("black")
                    && yourColors.contains("white")) {


                match = true;
            }

        }

        return match;


    }


        //check if array is full of all the same colors
        public static boolean AreAllSame(String[] array){

        for(int i = 1; i < array.length; i++){

            if(!array[0].equals(array[i])){
                return false;
            }


        }

        return true;
    }


    }


