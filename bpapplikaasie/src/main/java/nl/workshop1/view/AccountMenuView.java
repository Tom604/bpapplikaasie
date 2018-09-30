package nl.workshop1.view;

import java.util.ArrayList;
import nl.workshop1.controller.AccountController;
import nl.workshop1.domain.Account;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class AccountMenuView extends MenuView {
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Accounts\t\t");
            printHeader();
            
            System.out.println("1. Account toevoegen\n2. Account zoeken\n3. Account " +
                "aanpassen\n4. Account verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertAccountMenu(); break;
                case "2":   showSelectAccountMenu(); break;
                case "3":   showUpdateAccountMenu(); break;
                case "4":   showDeleteAccountMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void showInsertAccountMenu() {
        
        setViewName("Account toevoegen\t");
        printHeader();
        
        Klant klant = new Klant();
        String accounttype = getAccounttype();
        
        if (accounttype.equals("klant")) {
            KlantMenuView klantMenuView = new KlantMenuView();
            klant = klantMenuView.showSelectKlantMenu();
        }
        
        System.out.println("Voeg een nieuwe account toe aan de database.\n");
        System.out.print("Voer de username voor het account in (en druk dan op enter): ");
        String username = SCANNER.next();
        System.out.print("Voer het wachtwoord voor het account in (en druk dan op enter): ");
        String wachtwoord = SCANNER.next();
            
        System.out.println("\nHet opgegeven account:\nUsername:\t\t" + username +
                "\nWachtwoord:\t\t" + wachtwoord + "\nAccounttype:\t\t" + accounttype);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, opnieuw invoeren.\n");

        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.insertAccount(username, wachtwoord, accounttype, klant);
                        System.out.println("Account toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private String getAccounttype() {
        
        String accounttype = "";
        System.out.println("\nSelecteer accounttype:");
        System.out.println("1. Administrator\n2. Klant\n3. Medewerker\n");
        String selection = getSelection();
        switch (selection) {
            case "1":   accounttype = "admin"; break;
            case "2":   accounttype = "klant"; break;
            case "3":   accounttype = "medewerker";
        }
        return accounttype;
    }
    
    private Account showSelectAccountMenu() {
        
        setViewName("Account zoeken\t");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug");
        
        AccountController accountController = new AccountController();
        Account account = new Account();
        
        System.out.println("\nSelecteer account.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            account = accountController.selectAccount(selection);
            System.out.println("De geselecteerde account:");
            System.out.println(account.toString());
        }
            
        return account;
    }
    
    private int printList() {

        AccountController accountController = new AccountController();
        ArrayList<Account> accounts = accountController.selectAccounts();
        
        for (Account e: accounts) {
            System.out.println(e.getId() + ". " + e.toString());
        }
        return accounts.size();
    }
    
    private void showUpdateAccountMenu() {
        
        Account account = showSelectAccountMenu();
        setViewName("Account aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Username\n2. Wachtwoord\n3. Accounttype\n\n" +
                "0. Niets, terug naar Accountspagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe username: ");
                        String nieuweUsername = SCANNER.next();
                        showUpdatedAccountMenu(nieuweUsername, account.getWachtwoord(), account.getAccounttype(), account.getKlant());
                        break;
            case "2":   System.out.print("Nieuw wachtwoord: ");
                        String nieuwWachtwoord = SCANNER.next();
                        showUpdatedAccountMenu(account.getUsername(), nieuwWachtwoord, account.getAccounttype(), account.getKlant());
                        break;
            case "3":   System.out.print("Nieuw accounttype: ");
                        String nieuwAccounttype = SCANNER.next();
                        showUpdatedAccountMenu(account.getUsername(), account.getWachtwoord(), nieuwAccounttype, account.getKlant());
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedAccountMenu(String username, String wachtwoord, String accounttype, Klant klant) {
        
        System.out.println("\nDe aangepaste account:");
        System.out.println("Username:\t\t" + username + "\nWachtwoord:\t\t" + wachtwoord +
                "\nAccounttyppe:\t" + accounttype);
        
        System.out.println("\nWilt u de aangepaste account opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.updateAccount(username, wachtwoord, accounttype, klant);
                        System.out.println("Account opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteAccountMenu() {
        
        Account account = showSelectAccountMenu();
        int id = account.getId();
        
        setViewName("Account verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze account verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.deleteAccount(id);
                        System.out.println("Account verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
