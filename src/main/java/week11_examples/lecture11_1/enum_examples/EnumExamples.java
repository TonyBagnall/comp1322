package week11_examples.lecture11_1.enum_examples;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DecimalFormat;

/**
 * Use this class to do the enum exercise sheet
 Task 1:
 1.Add an enum typeColourto the classEnumExample.Colourshould have at leastthe seven colours of the rainbow.
 2.Write and test a static method to print out all the possible enum values and ordinal positionsofGradeandColour
 
 */
public class EnumExamples {
enum Grade{
FIRST(70), TWO_ONE(60), TWO_TWO(50), THIRD(40), FAIL(0);
	final int boundary;
	Grade(int x){
		boundary=x;
	}       
}

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("##.##");
		Country myCountry=Country.UK;
		Country otherCountry=Country.FRANCE;
		System.out.println(myCountry+" has GDP per head = $"+df.format(myCountry.gdpPerPerson()));
		System.out.println(myCountry+" has = "+df.format(100*myCountry.proportionOfTheWorldPop())+"% of the world population");
		System.out.println(myCountry+" has = "+df.format(100*myCountry.proportionOfTheWorldGDP())+"% of the world GDP");
		for (Planet p : Planet.values())
			System.out.println(p + " gravity = " + p.surfaceGravity());


	}
	public static boolean biggestCountry(Country a){
		if(a==Country.GERMANY)//Current biggest in the list
			return true;
		return false;
	}



}
