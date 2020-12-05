package program;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import board.detective.DetectiveName;
import board.district.Orientation;
import items.Actions;

public class InputListener {

	static Scanner sc;

	public InputListener() {
		sc = new Scanner(System.in);
	}

	public void close() {
		sc.close();
	}

	public DetectiveName getInputDetective() {

		System.out.println("Input a detective name:");
		String stringDetective = sc.next();
		boolean isValidInput = false;
		for (DetectiveName c : DetectiveName.values()) {
			if (c.name().equals(stringDetective.toUpperCase())) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			System.out.println("ok!");
			return DetectiveName.valueOf((stringDetective.toUpperCase()));
		} else {
			System.out.println("invalid name defaulted to SHERLOCK");
			return DetectiveName.SHERLOCK;
		}

	}
	
	public Actions getAction() {

		System.out.println("Input an action name:");
		String stringAction = sc.next();
		boolean isValidInput = false;
		for (Actions c : Actions.values()) {
			if (c.name().equals(stringAction.toUpperCase())) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			System.out.println("ok!");
			return Actions.valueOf((stringAction.toUpperCase()));
		} else {
			System.out.println("invalid name type again");
			return null;
		}

	}
	
	public void showJack() {
		System.out.println("Enter anything to reveal jack");
		@SuppressWarnings("unused")
		String stringReveal = sc.next();
	}
	
	public void hideJack() {
		System.out.println("Enter anything to hide jack");
		@SuppressWarnings("unused")
		String stringHide = sc.next();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	

	public Orientation getInputOrientation() {

		System.out.println("Input an orientation:");
		String stringOrientation = sc.next();
		boolean isValidInput = false;
		for (Orientation c : Orientation.values()) {
			if (c.name().equals(stringOrientation.toUpperCase())) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			System.out.println("ok!");
			return Orientation.valueOf((stringOrientation.toUpperCase()));
		} else {
			System.out.println("invalid name defaulted to EAST");
			return Orientation.EAST;
		}

	}


	public int getInputInt() {

		System.out.println("input an integer :");
		try {
		int integer = new Integer(sc.next());
		return integer;
		} catch (NumberFormatException e) {
			System.out.println("input was not an integer, resolved to -1");
		}
		return -1;
		
	}

	public List<Integer> getInputCoord() {
		System.out.println("Input coordinates");
		System.out.print("For X value ");
		int intx = Math.min(Math.max(1, getInputInt()), 3);
		System.out.print("For Y value ");
		int inty = Math.min(Math.max(1, getInputInt()), 3);
		return (Arrays.asList(intx, inty));
	}

}
