import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
   Programmer: Ian Luna Quiroz
   Assignment Chapter: Project Phase 4
   Purpose: Complete project
   Date modified: 19-3-2024
   IDE/Tool used: IntelliJ IDEA
 */

public class BankDriver {
    public static int centsFromDollarString(String dollarString) {
        StringBuilder amountCentsString = new StringBuilder();
        String[] tokenizedAmount = dollarString.split("\\.");
        amountCentsString.append(tokenizedAmount[0].trim());
        if (tokenizedAmount.length > 1) {
            if (tokenizedAmount[1].length() > 1) {
                amountCentsString.append(tokenizedAmount[1], 0, 2);
            } else {
                amountCentsString.append(tokenizedAmount[1]);
                amountCentsString.append("0");
            }
        } else {
            amountCentsString.append("00");
        }
        return Integer.parseInt(String.valueOf(amountCentsString));
    }

    public static String dollarStringFromCents(int cents) {
        StringBuilder centsString = new StringBuilder();
        if (cents < 100) {
            centsString.append("0.");
            if (cents < 10) {
                centsString.append("0");
            }
            centsString.append(cents);
        } else {
            centsString.append(cents);
            centsString.insert(centsString.length()-2, '.');
        }
        return String.valueOf(centsString);
    }

    public static void checkForExit(String input) throws IOException {
        if (input.equalsIgnoreCase("EXIT")) {
            throw new IOException("User exited program");
        }
    }

    public static void printCustomerRecords(File customerRecords, ArrayList<BankCustomer> bankCustomersList) {
        String customerRecordsAbsolutePath = customerRecords.getPath();
        System.out.println("""
                ╔══════════╦═════════════╦═════════════╦════════════════════════╦═════════════════════════╦══════════════╦═══════════════╗
                ║ RECORD # ║ CUSTOMER ID ║   BALANCE   ║   CUSTOMER FULL NAME   ║      EMAIL ADDRESS      ║ ACCOUNT TYPE ║ INTEREST RATE ║
                ╠══════════╬═════════════╬═════════════╬════════════════════════╬═════════════════════════╬══════════════╬═══════════════╣""");

        int counter = 0;
        for (BankCustomer currentCustomer : bankCustomersList) {
            counter++;
            int id = currentCustomer.getCustomerID();
            int balanceCents = currentCustomer.getBalanceCents();
            String balanceString = dollarStringFromCents(balanceCents);
            String name = currentCustomer.getFullName();
            String email = currentCustomer.getEmail();
            String acctType = currentCustomer.getAccountTypeString();
            double interestRate = currentCustomer.getAccountType().getInterestRatePercent();

            System.out.printf("""
                    ║ %8d ║ %11d ║ $%10s ║ %22s ║ %23s ║ %12s ║ %12.2f%% ║
                    """, counter, id, balanceString, name, email, acctType, interestRate);
        }
        System.out.printf("""
                ╠══════════╩═════════════╩═════════════╩════════════════════════╩═════════════════════════╩══════════════╩═══════════════╣
                ║ %d record(s) found in %-98s║
                ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
                """, counter, customerRecordsAbsolutePath);
    }

    public static void printCustomerRecord(BankCustomer currentCustomer) {
        System.out.println("""
                                    
                ╔═════════════╦═════════════╦════════════════════════╦═════════════════════════╦══════════════╦═══════════════╗
                ║ CUSTOMER ID ║   BALANCE   ║   CUSTOMER FULL NAME   ║      EMAIL ADDRESS      ║ ACCOUNT TYPE ║ INTEREST RATE ║
                ╠═════════════╬═════════════╬════════════════════════╬═════════════════════════╬══════════════╬═══════════════╣""");

        int id = currentCustomer.getCustomerID();
        int balanceCents = currentCustomer.getBalanceCents();
        String balanceString = dollarStringFromCents(balanceCents);
        String name = currentCustomer.getFullName();
        String email = currentCustomer.getEmail();
        String acctType = currentCustomer.getAccountTypeString();
        double interestRate = currentCustomer.getAccountType().getInterestRatePercent();

        System.out.printf("""
                ║ %11d ║ $%10s ║ %22s ║ %23s ║ %12s ║ %12.2f%% ║
                """, id, balanceString, name, email, acctType, interestRate);
        System.out.println("""
                ╚═════════════╩═════════════╩════════════════════════╩═════════════════════════╩══════════════╩═══════════════╝""");
    }

    public static void printPendingCustomerRecord(String fullName, String email, int accountTypeNum) {
        System.out.println("""
                                    
                ╔════════════════════════╦═════════════════════════╦══════════════╦═══════════════╗
                ║   CUSTOMER FULL NAME   ║      EMAIL ADDRESS      ║ ACCOUNT TYPE ║ INTEREST RATE ║
                ╠════════════════════════╬═════════════════════════╬══════════════╬═══════════════╣""");

        double interestRate;
        String accountTypeString;
        if (accountTypeNum == 1) {
            interestRate = 0.01;
            accountTypeString = "Checking";
        } else {
            interestRate = 0.02;
            accountTypeString = "Savings";
        }

        System.out.printf("""
                ║ %22s ║ %23s ║ %12s ║ %12.2f%% ║
                """, fullName, email, accountTypeString, interestRate);
        System.out.println("""
                ╚════════════════════════╩═════════════════════════╩══════════════╩═══════════════╝""");
    }

    public static void depositToBankCustomer(BankCustomer currentCustomer) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("""
                \nHow much would you like to deposit?
                → $""");
        String selection = keyboard.nextLine();
        checkForExit(selection);

        try {
            int depositAmount = centsFromDollarString(selection);
            do {

                System.out.printf("""
                                            
                        ╔════════════════════════╦════════════════════════╦════════════════════════╗
                        ║    PREVIOUS BALANCE    ║     DEPOSIT AMOUNT     ║       NEW BALANCE      ║
                        ╠════════════════════════╬════════════════════════╬════════════════════════╣
                        ║ $%21s ║ $%21s ║ $%21s ║
                        ╚════════════════════════╩════════════════════════╩════════════════════════╝
                        Continue with deposit?
                        \tYes (Y)...
                        \tNo (N)...
                        →\s""", dollarStringFromCents(currentCustomer.getBalanceCents()), dollarStringFromCents(depositAmount), dollarStringFromCents(currentCustomer.getBalanceCents() + depositAmount));
                selection = keyboard.nextLine();
                checkForExit(selection);

            } while (!(selection.equalsIgnoreCase("Y") || selection.equalsIgnoreCase("N")));
            if (selection.equalsIgnoreCase("Y")) {
                currentCustomer.deposit(depositAmount);

                System.out.printf("""
                        \nDeposited $%s
                        Press ENTER to continue... ⏎""", dollarStringFromCents(depositAmount));
                keyboard.nextLine();
            } else {
                System.out.print("""
                        \nDeposit Cancelled
                        Press ENTER to continue... ⏎""");
                keyboard.nextLine();
            }

        } catch (NumberFormatException e) {
            System.out.print("""
                    \n(!) That's not a number (!)
                    Press ENTER to continue... ⏎""");
            keyboard.nextLine();
        }
    }

    public static void withdrawFromBankCustomer(BankCustomer currentCustomer, boolean isSuper) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        if (!isSuper && currentCustomer.getBalanceCents() <= 0) {
            System.out.print("""
                    \nCan't withdraw any money...
                    You're broke!
                    Press ENTER to continue... ⏎""");
            keyboard.nextLine();
        } else {
            System.out.print("""
                    \nHow much would you like to withdraw?
                    → $""");
            String selection = keyboard.nextLine();
            checkForExit(selection);

            try {
                int withdrawAmountCents = centsFromDollarString(selection);

                if (!isSuper && (withdrawAmountCents > currentCustomer.getBalanceCents())) {
                    System.out.printf("""
                            \nYou can't withdraw that much!...
                            Your current balance is $%s
                            Press ENTER to continue... ⏎""", dollarStringFromCents(currentCustomer.getBalanceCents()));
                    keyboard.nextLine();
                } else {
                    do {
                        System.out.printf("""
                                                    
                                ╔════════════════════════╦════════════════════════╦════════════════════════╗
                                ║    PREVIOUS BALANCE    ║     WITHDRAW AMOUNT    ║       NEW BALANCE      ║
                                ╠════════════════════════╬════════════════════════╬════════════════════════╣
                                ║ $%21s ║ $%21s ║ $%21s ║
                                ╚════════════════════════╩════════════════════════╩════════════════════════╝
                                Continue with withdrawal?
                                \tYes (Y)...
                                \tNo (N)...
                                →\s""", dollarStringFromCents(currentCustomer.getBalanceCents()), dollarStringFromCents(withdrawAmountCents), dollarStringFromCents(currentCustomer.getBalanceCents() - withdrawAmountCents));
                        selection = keyboard.nextLine();
                        checkForExit(selection);

                    } while (!(selection.equalsIgnoreCase("Y") || selection.equalsIgnoreCase("N")));
                    if (selection.equalsIgnoreCase("Y")) {
                        currentCustomer.withdraw(withdrawAmountCents);

                        System.out.printf("""
                                \nWithdrew $%s
                                Press ENTER to continue... ⏎""", dollarStringFromCents(withdrawAmountCents));
                        keyboard.nextLine();
                    } else {
                        System.out.print("""
                                \nWithdrawal Cancelled
                                Press ENTER to continue... ⏎""");
                        keyboard.nextLine();
                    }
                }

            } catch (NumberFormatException e) {
                System.out.print("""
                        \n(!) That's not a number (!)
                        Press ENTER to continue... ⏎""");
                keyboard.nextLine();
            }
        }
    }

    public static void writeBackupFile(File customerRecords, ArrayList<BankCustomer> bankCustomersList) throws IOException {
        PrintWriter customerRecordsPrinter = new PrintWriter(customerRecords);

        for (BankCustomer currentCustomer : bankCustomersList) {
            customerRecordsPrinter.println(currentCustomer);
        }

        customerRecordsPrinter.close();
    }

    public static void main(String[] args) throws IOException {
        AccountType[] accountTypesList = new AccountType[2];
        accountTypesList[0] = new AccountType(1); // Checking account
        accountTypesList[1] = new AccountType(2); // Savings account

        File customerRecords = new File("BankCustInfo.txt");

        PrintWriter customerRecordsPrinter1 = new PrintWriter(customerRecords); // This clears the existing data in BankCustInfo.txt
        customerRecordsPrinter1.close();

        ArrayList<BankCustomer> bankCustomersList = new ArrayList<BankCustomer>(); //TODO load with data from JSON

        Scanner keyboard = new Scanner(System.in);
        String selection;

        try {
            do {
                System.out.print("""
                        \n═══════════════ Welcome to Bank Simulator! ═══════════════
                             Type "exit" at any time to exit the program
                             
                        What would you like to do?
                        \tUse manager functions (M)...
                        \tUse customer functions (C)...
                        \tor View help page (H)...
                        →\s""");

                selection = keyboard.next().toUpperCase();
                checkForExit(selection);

                switch (selection.charAt(0)) {
                    case 'M' -> {
                        do {
                            System.out.print("""
                                    \nWhat would you like to do as manager?
                                    \tView all customer records (R)...
                                    \tEdit customer records (E)...
                                    \tAdd a new customer (A)...
                                    \tGo back (B)...
                                    →\s""");
                            selection = keyboard.next().toUpperCase();
                            checkForExit(selection);

                            switch (selection.charAt(0)) {
                                case 'R' -> {
                                    System.out.println();
                                    printCustomerRecords(customerRecords, bankCustomersList);
                                    System.out.print("Press ENTER to continue... ⏎");
                                    keyboard.nextLine();
                                    keyboard.nextLine();
                                }
                                case 'A' -> {
                                    int newCustomerAccountTypeNum = 0;
                                    String newCustomerFullName = "";
                                    String newCustomerEmail = "";

                                    keyboard.nextLine();
                                    do {
                                        System.out.print("""
                                                \nAdding a new customer!
                                                Type "continue" to continue,
                                                 or "cancel" to cancel...
                                                →\s""");
                                        selection = keyboard.nextLine();
                                        checkForExit(selection);
                                    } while (!(selection.equalsIgnoreCase("CONTINUE") || selection.equalsIgnoreCase("CANCEL")));

                                    if (!(selection.equalsIgnoreCase("CANCEL"))) {
                                        do {
                                            System.out.print("\nEnter the customer's full name → ");
                                            newCustomerFullName = keyboard.nextLine();
                                            checkForExit(newCustomerFullName);

                                            System.out.print("Enter the customers email address → ");
                                            newCustomerEmail = keyboard.nextLine();
                                            checkForExit(newCustomerEmail);

                                            do {
                                                System.out.print(("""
                                                        \nWhat kind of account will this customer have?
                                                        \tChecking (1)...
                                                        \tSavings (2)...
                                                        →\s"""));
                                                selection = keyboard.nextLine();
                                                checkForExit(selection);
                                                if (!(selection.equals("1") || selection.equals("2"))) {
                                                    System.out.print("""
                                                            \nNot a valid selection...
                                                            Press ENTER to continue... ⏎""");
                                                    keyboard.nextLine();
                                                }
                                            } while (!(selection.equals("1") || selection.equals("2")));
                                            newCustomerAccountTypeNum = Integer.parseInt(selection);

                                            do {
                                                printPendingCustomerRecord(newCustomerFullName, newCustomerEmail, newCustomerAccountTypeNum);
                                                System.out.print("""
                                                        \nIf all the information is accurate, type "continue",
                                                        if the information is inaccurate, type "redo",
                                                        or type "cancel" to cancel...
                                                        →\s""");
                                                selection = keyboard.nextLine();
                                                checkForExit(selection);

                                            } while (!(selection.equalsIgnoreCase("CONTINUE") || selection.equalsIgnoreCase("REDO") || selection.equalsIgnoreCase("CANCEL")));
                                        } while (selection.equalsIgnoreCase("REDO"));
                                    }

                                    if (!(selection.equalsIgnoreCase("CANCEL"))) {
                                        try {
                                            Scanner customerRecordsScanner = new Scanner(customerRecords);
                                            int lineCounter = 0;
                                            while (customerRecordsScanner.hasNextLine()) {
                                                customerRecordsScanner.nextLine();
                                                ++lineCounter;
                                            }
                                            lineCounter++;
                                            BankCustomer newCustomer = new BankCustomer(newCustomerFullName, newCustomerEmail, accountTypesList[newCustomerAccountTypeNum - 1], lineCounter);
                                            bankCustomersList.add(newCustomer);

                                            writeBackupFile(customerRecords, bankCustomersList);

                                            System.out.println("\nSuccessfully created a new customer account!");
                                            printCustomerRecord(bankCustomersList.get(bankCustomersList.size() - 1));
                                            System.out.print("Press ENTER to continue... ⏎");
                                            keyboard.nextLine();

                                        } catch (FileNotFoundException e) {
                                            System.out.print("""
                                                    \n(!) File does not exist (!)
                                                    Press ENTER to continue... ⏎""");
                                            keyboard.nextLine();
                                        }
                                    } else {
                                        System.out.print("""
                                                \nAccount creation canceled!
                                                Press ENTER to continue... ⏎""");
                                        keyboard.nextLine();
                                    }
                                }
                                case 'E' -> {
                                    if (bankCustomersList.isEmpty()) {
                                        System.out.print("""
                                                \nThere aren't any records to edit!
                                                Press ENTER to continue... ⏎""");
                                        keyboard.nextLine();
                                        keyboard.nextLine();
                                    } else {
                                        int customerRecordSelection = 0;
                                        keyboard.nextLine();
                                        do {
                                            printCustomerRecords(customerRecords, bankCustomersList);
                                            System.out.print("""
                                                    Type the number of the record you'd like to edit,
                                                    or Go back (B)...
                                                    →\s""");
                                            selection = keyboard.next();
                                            checkForExit(selection);

                                            if (!(selection.equalsIgnoreCase("B"))) {
                                                try {
                                                    customerRecordSelection = Integer.parseInt(selection);

                                                    if (customerRecordSelection < 1 || customerRecordSelection > bankCustomersList.size()) {
                                                        System.out.print("""
                                                                \nThat record doesn't exist...
                                                                Press ENTER to continue... ⏎""");
                                                        keyboard.nextLine();
                                                        keyboard.nextLine();
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.print("""
                                                            \n(!) That's not a number (!)
                                                            Press ENTER to continue... ⏎""");
                                                    keyboard.nextLine();
                                                    keyboard.nextLine();
                                                }
                                            } else {
                                                selection = selection.toUpperCase();
                                            }

                                        } while ((customerRecordSelection < 1 || customerRecordSelection > bankCustomersList.size()) && !(selection.charAt(0) == 'B'));

                                        if (!(selection.charAt(0) == 'B')) {
                                            do {
                                                System.out.printf("\nYou've selected Record %d...", customerRecordSelection);
                                                printCustomerRecord(bankCustomersList.get(customerRecordSelection - 1));

                                                System.out.print("""
                                                        What do you want to do to this customer?
                                                        \tChange name (N)...
                                                        \tChange email address (E)...
                                                        \tChange account type (A)...
                                                        \tDeposit money (D)...
                                                        \tWithdraw money (W)...
                                                        \tGo back (B)...
                                                        →\s""");
                                                selection = keyboard.next().toUpperCase();
                                                checkForExit(selection);

                                                switch (selection.charAt(0)) {
                                                    case 'N' -> {
                                                        System.out.print("""
                                                                \nEnter the new name,
                                                                or Go back (B)...
                                                                →\s""");
                                                        keyboard.nextLine();
                                                        selection = keyboard.nextLine();
                                                        checkForExit(selection);

                                                        if (!(selection.equalsIgnoreCase("B"))) {
                                                            String newName = selection;
                                                            do {
                                                                System.out.printf("""
                                                                                            
                                                                        ╔════════════════════════╦════════════════════════╗
                                                                        ║        OLD NAME        ║        NEW NAME        ║
                                                                        ╠════════════════════════╬════════════════════════╣
                                                                        ║ %22s ⇒ %-22s ║
                                                                        ╚════════════════════════╩════════════════════════╝
                                                                        Are you sure you want to make this change?
                                                                        \tYes (Y)...
                                                                        \tNo (N)...
                                                                        →\s""", bankCustomersList.get(customerRecordSelection - 1).getFullName(), newName);
                                                                selection = keyboard.next().toUpperCase();
                                                                checkForExit(selection);

                                                            } while (!(selection.charAt(0) == 'Y' || selection.charAt(0) == 'N'));
                                                            if (selection.charAt(0) == 'Y') {
                                                                bankCustomersList.get(customerRecordSelection - 1).setFullName(newName);

                                                                writeBackupFile(customerRecords, bankCustomersList);

                                                                System.out.printf("\nRecord %d modified!", customerRecordSelection);
                                                                printCustomerRecord(bankCustomersList.get(customerRecordSelection - 1));
                                                                System.out.print("Press ENTER to continue... ⏎");
                                                            } else {
                                                                System.out.print("""
                                                                        \nChange Cancelled
                                                                        Press ENTER to continue... ⏎""");
                                                            }
                                                            keyboard.nextLine();
                                                            keyboard.nextLine();
                                                        }

                                                        selection = "";
                                                    }
                                                    case 'E' -> {
                                                        System.out.print("""
                                                                \nEnter the new email address,
                                                                or Go back (B)...
                                                                →\s""");
                                                        keyboard.nextLine();
                                                        selection = keyboard.nextLine();
                                                        checkForExit(selection);

                                                        if (!(selection.equalsIgnoreCase("B"))) {
                                                            String newEmail = selection;
                                                            do {
                                                                System.out.printf("""
                                                                                            
                                                                        ╔════════════════════════╦════════════════════════╗
                                                                        ║        OLD EMAIL       ║        NEW EMAIL       ║
                                                                        ╠════════════════════════╬════════════════════════╣
                                                                        ║ %22s ⇒ %-22s ║
                                                                        ╚════════════════════════╩════════════════════════╝
                                                                        Are you sure you want to make this change?
                                                                        \tYes (Y)...
                                                                        \tNo (N)...
                                                                        →\s""", bankCustomersList.get(customerRecordSelection - 1).getEmail(), newEmail);
                                                                selection = keyboard.next().toUpperCase();
                                                                checkForExit(selection);

                                                            } while (!(selection.charAt(0) == 'Y' || selection.charAt(0) == 'N'));
                                                            if (selection.charAt(0) == 'Y') {
                                                                bankCustomersList.get(customerRecordSelection - 1).setEmail(newEmail);

                                                                writeBackupFile(customerRecords, bankCustomersList);

                                                                System.out.printf("\nRecord %d modified!", customerRecordSelection);
                                                                printCustomerRecord(bankCustomersList.get(customerRecordSelection - 1));
                                                                System.out.print("Press ENTER to continue... ⏎");
                                                            } else {
                                                                System.out.print("""
                                                                        \nChange Cancelled
                                                                        Press ENTER to continue... ⏎""");
                                                            }
                                                            keyboard.nextLine();
                                                            keyboard.nextLine();
                                                        }
                                                        selection = "";
                                                    }
                                                    case 'A' -> {
                                                        keyboard.nextLine();
                                                        do {
                                                            System.out.print("""
                                                                    \nChoose account type,
                                                                    \tChecking (1)...
                                                                    \tSavings (2)...
                                                                    \tor Go back (B)...
                                                                    →\s""");
                                                            selection = keyboard.nextLine();
                                                            checkForExit(selection);
                                                            if (!(selection.equals("1") || selection.equals("2")) && !(selection.equalsIgnoreCase("B"))) {
                                                                System.out.print("""
                                                                        \nNot a valid account type...
                                                                        Press ENTER to continue... ⏎""");
                                                                keyboard.nextLine();
                                                            }
                                                        } while (!(selection.equals("1") || selection.equals("2")) && !(selection.equalsIgnoreCase("B")));

                                                        if (!(selection.equalsIgnoreCase("B"))) {
                                                            int newAccountTypeNum = Integer.parseInt(selection);

                                                            do {
                                                                System.out.printf("""
                                                                                            
                                                                        ╔════════════════════════╦════════════════════════╗
                                                                        ║    OLD ACCOUNT TYPE    ║    NEW ACCOUNT TYPE    ║
                                                                        ╠════════════════════════╬════════════════════════╣
                                                                        ║ %22s ⇒ %-22s ║
                                                                        ╚════════════════════════╩════════════════════════╝
                                                                        Are you sure you want to make this change?
                                                                        \tYes (Y)...
                                                                        \tNo (N)...
                                                                        →\s""", bankCustomersList.get(customerRecordSelection - 1).getAccountTypeString(), accountTypesList[newAccountTypeNum - 1].getAccountTypeString());
                                                                selection = keyboard.next().toUpperCase();
                                                                checkForExit(selection);

                                                            } while (!(selection.charAt(0) == 'Y' || selection.charAt(0) == 'N'));
                                                            if (selection.charAt(0) == 'Y') {
                                                                bankCustomersList.get(customerRecordSelection - 1).setAccountType(accountTypesList[newAccountTypeNum - 1]);

                                                                writeBackupFile(customerRecords, bankCustomersList);

                                                                System.out.printf("\nRecord %d modified!", customerRecordSelection);
                                                                printCustomerRecord(bankCustomersList.get(customerRecordSelection - 1));
                                                                System.out.print("Press ENTER to continue... ⏎");
                                                            } else {
                                                                System.out.print("""
                                                                        \nChange Cancelled
                                                                        Press ENTER to continue... ⏎""");
                                                            }
                                                            keyboard.nextLine();
                                                            keyboard.nextLine();
                                                        }
                                                        selection = "";
                                                    }
                                                    case 'D' -> {
                                                        depositToBankCustomer(bankCustomersList.get(customerRecordSelection - 1));
                                                        writeBackupFile(customerRecords, bankCustomersList);
                                                    }
                                                    case 'W' -> {
                                                        withdrawFromBankCustomer(bankCustomersList.get(customerRecordSelection - 1), true);
                                                        writeBackupFile(customerRecords, bankCustomersList);
                                                    }
                                                }
                                            } while (!(selection.equalsIgnoreCase("B")));
                                        }
                                    }
                                    selection = "";
                                }
                            }
                        } while (!(selection.equalsIgnoreCase("B")));
                        selection = "";
                    }
                    case 'C' -> {
                        if (bankCustomersList.isEmpty()) {
                            System.out.print("""
                                    \nThere aren't any customers yet!...
                                    Use the manager menu to add customers...
                                    Press ENTER to continue... ⏎""");
                            keyboard.nextLine();
                            keyboard.nextLine();
                        } else {
                            try {
                                System.out.print("\nEnter your customer ID...\n→ ");
                                keyboard.nextLine();
                                selection = keyboard.nextLine();
                                checkForExit(selection);
                                int customerID = Integer.parseInt(selection);

                                BankCustomer currentCustomer = bankCustomersList.get(customerID - 1);

                                do {
                                    System.out.printf("""
                                            \nWelcome %s!
                                            What would you like to do today?
                                            \tDeposit (D)...
                                            \tWithdraw (W)...
                                            \tEarn Interest (E)...
                                            \tView Account Information (I)...
                                            \tGo back (B)...
                                            →\s""", currentCustomer.getFullName());
                                    selection = keyboard.next().toUpperCase();
                                    checkForExit(selection);

                                    switch (selection.charAt(0)) {
                                        case 'D' -> {
                                            depositToBankCustomer(currentCustomer);
                                            writeBackupFile(customerRecords, bankCustomersList);
                                        }
                                        case 'W' -> {
                                            withdrawFromBankCustomer(currentCustomer, false);
                                            writeBackupFile(customerRecords, bankCustomersList);
                                        }
                                        case 'E' -> {
                                            if (currentCustomer.getBalanceCents() <= 0) {
                                                System.out.print("""
                                                        \nYou don't have a balance to earn interest on!
                                                        Press ENTER to continue... ⏎""");
                                            } else {
                                                int preBalance = currentCustomer.getBalanceCents();
                                                int interest = currentCustomer.calculateInterestEarned();
                                                System.out.printf("""
                                                                                                                
                                                        You earned interest!
                                                        ╔════════════════════════╦════════════════════════╦════════════════════════╗
                                                        ║    PREVIOUS BALANCE    ║     INTEREST EARNED    ║       NEW BALANCE      ║
                                                        ╠════════════════════════╬════════════════════════╬════════════════════════╣
                                                        ║ $%21s ║ $%21s ║ $%21s ║
                                                        ╚════════════════════════╩════════════════════════╩════════════════════════╝
                                                        Press ENTER to continue... ⏎""", dollarStringFromCents(preBalance), dollarStringFromCents(interest), dollarStringFromCents(currentCustomer.getBalanceCents()));
                                            }
                                            keyboard.nextLine();
                                            keyboard.nextLine();
                                        }
                                        case 'I' -> {
                                            printCustomerRecord(currentCustomer);
                                            System.out.print("Press ENTER to continue... ⏎");
                                            keyboard.nextLine();
                                            keyboard.nextLine();
                                        }
                                    }
                                } while (!(selection.equalsIgnoreCase("B")));

                            } catch (IndexOutOfBoundsException e) {
                                System.out.print("""
                                        \n(!) That customer ID doesn't exist (!)
                                        Press ENTER to continue... ⏎""");
                                keyboard.nextLine();
                            }
                        }
                        selection = "";
                    }
                    case 'H' -> {
                        System.out.print("""
                                \nThis program simulates a bank.
                                As a manager you can view all customer records, edit records, and add new customers.
                                As a customer you can view your account information, deposit money, and withdraw money.
                                Currently, each customer only has one account.
                                Customer records are backed-up in "BankCustInfo.txt" and are deleted every time the program is started.
                                Typing "exit" anytime you're prompted for text, will cause the program to exit.
                                                                
                                Press ENTER to continue... ⏎""");
                        keyboard.nextLine();
                        keyboard.nextLine();
                    }

                }
            } while (!(selection.equals("EXIT")));
        } catch (IOException e) {
            System.out.println("\nExiting program...");
        }
    }
}
