/*
 * CS312 Assignment 1.
 * On my honor, David Alvarez, this programming assignment is my own work.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: David Alvarez
 *  email address: David.alvarez@utexas.edu
 *  UTEID:da28443
 *  Section 5 digit ID: 50724
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

public class Song {


// print out the lyrics of "There was an old woman ... "
	public static void main(String[] args) {
		fly();
		spider();
		bird();
		cat();
		dog();
		goat();
		cow();
		horse();
	
	}
// The function prints out the ending part of the verse 
	public static void endingOfVerse() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.\n");
	}
// this function prints the fly verse/ 1st version of the repetitive story portion
	public static void fly() {
		System.out.println("There was an old woman who swallowed a fly.");
		endingOfVerse();
	}
// This function prints the start of the repetitive story portion of the spider verse
	public static void spiderToFly() {
		System.out.println("She swallowed the spider to catch the fly,");
		endingOfVerse();
	}
//This function is the prints the unique spider verse
	public static void spider() {
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		spiderToFly();
	}
//This function adds the bird verse to the repeating song verse 
	public static void birdToSpider() {
		System.out.println("She swallowed the bird to catch the spider,");
		spiderToFly();
	}
//This function prints the unique bird verse along with the repeating story portion
	public static void bird() {
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		birdToSpider();
	}
//This function adds the cat portion to the repeating  story portion of the verse 
	public static void catToBird() {
		System.out.println("She swallowed the cat to catch the bird,");
		birdToSpider();
	}
//This prints the unique cat verse
	public static void cat() {
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		catToBird();
	}
//This function adds the dog portion to the repeating  story portion of the verse 
	public static void dogToCat() {
		System.out.println("She swallowed the dog to catch the cat,");
		catToBird();
	}
// This function prints the whole dog verse 
	public static void dog() {
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		dogToCat();
	}
//This function adds the goat portion to the repeating  story portion of the verse 
	public static void goatToDog() {
		System.out.println("She swallowed the goat to catch the dog,");
		dogToCat();
	}
//This function prints the whole goat verse
	public static void goat() {
		System.out.println("There was an old woman who swallowed a goat,");
		System.out.println("She just opened her throat to swallow a goat.");
		goatToDog();
	}
//This function adds the cow portion to the repeating  story portion of the verse 
	public static void cowToGoat() {
		System.out.println("She swallowed the cow to catch the goat,");
		goatToDog();
	}
//This function prints the whole cow verse 
	public static void cow() {
		System.out.println("There was an old woman who swallowed a cow,");
		System.out.println("I don't know how she swallowed a cow.");
		cowToGoat();
	}
//This function prints the last verse which is the horse verse
	public static void horse() {
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}
	
	
}
