package nl.workshop1.view;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vosjes
 */
public abstract class MenuView {
    
    protected final String MAINTOPBOTTOM = "*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*";
    protected final String MAINHEADER1 = "Boer Piets kazen";
    protected final String MAINHEADER2 = "Ingelogd als ";
    protected final String MAINCHOICE = "Maak uw keuze (voer het getal in en druk dan op enter): ";
    protected final String MAINERROR = "Uw invoer is onjuist. Probeer het opnieuw.";
    
    /**
     * De input getter voor Strings.
     * @param printString De waarde die geprint moet worden door deze methode.
     * @return De String input.
     */
    protected String getStringInput(String printString) {
        Scanner input = new Scanner(System.in);
        System.out.print(printString);
        return input.nextLine();
    }
    
    /**
     * De input getter voor integers, gebruikt voor keuzemenu's; controleert of er
     * een InputMismatchException plaatsvindt.
     * @param printString De waarde die geprint moet worden door deze methode.
     * @param aantal Het aantal keuzes uit het keuzemenu (zonder 0) waarvoor de 
     * input en return value gevraagd worden.
     * @return De int input, -1 voor foutieve input.
     */
    protected int getIntInput(String printString, int aantal) {
        int temp = 0;
        try {
            Scanner input = new Scanner(System.in);
            System.out.print(printString);
            temp = input.nextInt();
            if (temp > aantal || temp < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            System.out.println(MAINERROR);
        }
        return temp;
    }
   
    protected BigDecimal getBigDecimalInput(String printString) {
        Scanner input = new Scanner(System.in);
        System.out.print(printString);
        return input.nextBigDecimal();
    }
    
    public abstract void showMenu(String loginNaam);
}
