package parser;

import java.util.*;

public class Testing {
	public static void main(String args[]) {

		String string;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter expression:");
		string = scan.nextLine();
		scan.close();
		String[] uiInput = string.split(" ");
		ExpressionUI.main(uiInput);
	}
}