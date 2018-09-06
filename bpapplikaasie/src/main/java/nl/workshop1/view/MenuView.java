package nl.workshop1.view;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Vosjes
 */
public abstract class MenuView {
    
    protected final static String MAINTOPBOTTOM =
            "*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*";
    protected final static String MAINHEADER = "Boer Piets kazen";
    protected final static String MAINCHOICE =
            "Maak uw keuze (voer het getal in en druk dan op enter): ";
    
    protected String getStringInput(String printString) {
        Scanner input = new Scanner(System.in);
        System.out.print(printString);
        return input.nextLine();
    }
    
    protected int getIntInput(String printString) {
        Scanner input = new Scanner(System.in);
        System.out.print(printString);
        return input.nextInt();
    }
    
    protected BigDecimal getBigDecimalInput(String printString) {
        Scanner input = new Scanner(System.in);
        System.out.print(printString);
        return input.nextBigDecimal();
    }
    
    public abstract void showMenu();
}
