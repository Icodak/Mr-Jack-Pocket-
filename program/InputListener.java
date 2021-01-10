package program;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import board.detective.DetectiveName;
import board.district.Orientation;
import items.Actions;

public class InputListener {
	// Temporary class to get info from console
	static Scanner sc = new Scanner(System.in);

	public void close() {
		// Close the scanner
		sc.close();
	}

	public DetectiveName getInputDetective() {
		System.out.println("Input a detective name:");
		String stringDetective = sc.next();
		boolean isValidInput = false;
		for (DetectiveName c : DetectiveName.values()) {
			if (c.name().equalsIgnoreCase(stringDetective)) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			return DetectiveName.valueOf((stringDetective.toUpperCase()));
		} else {
			System.out.println("invalid name defaulted to SHERLOCK");
			return DetectiveName.SHERLOCK;
		}
	}

	public Actions getAction(String actionName) {
		//System.out.println("Input an action name:");
		String stringAction = actionName;
		boolean isValidInput = false;
		for (Actions c : Actions.values()) {
			if (c.name().equalsIgnoreCase(stringAction)) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			return Actions.valueOf((stringAction.toUpperCase()));
		} else {
			System.out.println("invalid name type again");
			return null;
		}
	}

	public void showJack() {
		System.out.println("Enter anything to reveal jack");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void hideJack() {
		System.out.println("Enter anything to hide jack");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n\n\n\n\n\n\n\n");
	}

	public Orientation getInputOrientation() {
		System.out.println("Input an orientation:");
		String stringOrientation = sc.next();
		boolean isValidInput = false;
		for (Orientation c : Orientation.values()) {
			if (c.name().equalsIgnoreCase(stringOrientation)) {
				isValidInput = true;
			}
		}
		if (isValidInput) {
			return Orientation.valueOf((stringOrientation.toUpperCase()));
		} else {
			System.out.println("invalid name defaulted to EAST");
			return Orientation.EAST;
		}
	}

	public int getInputInt() {
		System.out.println("input an integer :");
		try {
			return new Integer(sc.next());
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
