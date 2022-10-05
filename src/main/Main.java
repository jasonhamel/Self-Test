package main;

import main.model.account.Account;
import main.model.account.Personal;
import main.model.account.TFSA;
import main.model.stock.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static HashMap<Integer, Double> aaplMap = new HashMap<>();
    static HashMap<Integer, Double> fbMap = new HashMap<>();
    static HashMap<Integer, Double> googMap = new HashMap<>();
    static HashMap<Integer, Double> tslaMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        Account[] accounts = new Account[]{
                new Personal(1000),
                new TFSA(1000)
        };
        importPrices();

        AAPL aapl = new AAPL(0);
        FB fb = new FB(0);
        GOOG goog = new GOOG(0);
        TSLA tsla = new TSLA(0);
        Scanner scan = new Scanner(System.in);

        System.out.println("Each day we will update the price of each stock. You will be able to execute an unlimited");
        System.out.println("number of trades. When you are done trading for the day, you can end the day to move on");
        System.out.println("Any \"sell\" made from the Personal account will be charged a 5% fee.");
        System.out.println("All transactions made from the TFSA account will be charged a 1% fee.");
        System.out.println("This includes both \"buy\" and \"sell\" transactions");
        System.out.println("Both accounts have a starting balance of 1000");

        int day = 0;
        while (true) {
            day++;
            updatePrices(aapl, fb, goog, tsla, day);
            System.out.println(accounts[0]);
            System.out.println(accounts[1]);
            System.out.println("\tDay " + day);
            System.out.println("\tAAPL is worth $" + aapl.getPriceOfShare() + " per share today.");
            System.out.println("\tFB is worth $" + fb.getPriceOfShare() + " per share today.");
            System.out.println("\tGOOG is worth $" + goog.getPriceOfShare() + " per share today.");
            System.out.println("\tTSLA is worth $" + tsla.getPriceOfShare() + " per share today.\n");

            while (true) {
                System.out.print("Would you like to buy or sell any shares today? Please type either \"yes\" or \"no\" or \"quit\"...");
                String responseBuyOrSell = scan.nextLine();

                if (responseBuyOrSell.equalsIgnoreCase("yes")) {
                    System.out.print("Would you like to buy or sell? Please type either \"buy\" or \"sell\"...");
                    String buyOrSell = scan.nextLine();

                    if (buyOrSell(buyOrSell)) {
                        System.out.println("Excellent! Which stock would you like to take action on?");
                        System.out.print("Please enter the ticker you'd like to action \"aapl\", \"fb\", \"goog\", \"tsla\"...");
                        String whichStock = scan.nextLine();

                        if (whichStock(whichStock)) {

                            //  while (true) {
                            System.out.print("Would you like to action this for your personal account or tfsa? \"personal\" or \"tfsa\"...");
                            String accountToBuy = scan.nextLine();

                            if (accountToBuy(accountToBuy)) {
                                System.out.print("How many shares would you like to action?...");

                                int numberOfShares = scan.nextInt();
                                if (numberOfShares < 0) {
                                    System.out.println("Cannot action negative shares");
                                    break;
                                } else {
                                    if (accounts[personalOrTFSA(accountToBuy)]
                                            .action(buyOrSell, stockToBuy(whichStock, aapl, fb, goog, tsla), numberOfShares)) {
                                        System.out.println("Purchased successfully!");
                                    } else {
                                        System.out.println("There was an error with the trade, please check your entries.");
                                    }
                                    scan.nextLine();
                                }
                                //  }
                                break;
                            }
                        }
                    } else {
                        System.out.println("Invalid entry, try again");
                        scan.nextLine();
                    }
                } else if (responseBuyOrSell.equalsIgnoreCase("no")) {
                    System.out.println("Business has closed for today. Let's see how those trades look tomorrow\n\n");
                    break;
                } else if (responseBuyOrSell.equalsIgnoreCase("quit")) {
                    System.exit(0);
                    break;
                } else {
                    System.out.println("Invalid entry, please try again.");
                }
            }
        }
    }

    public static void importPrices() throws FileNotFoundException {
        FileInputStream fis;

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                fis = new FileInputStream("src/main/data/AAPL.csv");
            } else if (i == 1) {
                fis = new FileInputStream("src/main/data/FB.csv");
            } else if (i == 2) {
                fis = new FileInputStream("src/main/data/GOOG.csv");
            } else {
                fis = new FileInputStream("src/main/data/TSLA.csv");
            }
            Scanner scan = new Scanner(fis);
            scan.useDelimiter(Pattern.compile("[\r\n,]"));
            scan.nextLine();

            while (scan.hasNext()) {
                if (i == 0) {
                    aaplMap.put(scan.nextInt(), scan.nextDouble());
                } else if (i == 1) {
                    fbMap.put(scan.nextInt(), scan.nextDouble());
                } else if (i == 2) {
                    googMap.put(scan.nextInt(), scan.nextDouble());
                } else {
                    tslaMap.put(scan.nextInt(), scan.nextDouble());
                }
            }
            scan.close();
        }
    }

    public static void updatePrices(Stock aapl, Stock fb, Stock goog, Stock tsla, int day) {
        aapl.setPrice(aaplMap.get(day));
        fb.setPrice(fbMap.get(day));
        goog.setPrice(googMap.get(day));
        tsla.setPrice(tslaMap.get(day));
    }

    public static boolean whichStock(String whichStock) {
        if (whichStock.equalsIgnoreCase("aapl")) {
            return true;
        } else if (whichStock.equalsIgnoreCase("fb")) {
            return true;
        } else if (whichStock.equalsIgnoreCase("goog")) {
            return true;
        } else if (whichStock.equalsIgnoreCase("tsla")) {
            return true;
        }
        System.out.println("Invalid entry, please try again.");
        return false;
    }

    public static boolean accountToBuy(String accountToBuy) {
        if (accountToBuy.equalsIgnoreCase("personal")) {
            return true;
        }
        if (accountToBuy.equalsIgnoreCase("tfsa")) {
            return true;
        }
        return false;
    }

    public static int personalOrTFSA(String accountToBuy) {
        if (accountToBuy.equalsIgnoreCase("personal")) {
            return 0;
        }
        return 1;
    }

    public static Stock stockToBuy(String whichStock, Stock aapl, Stock fb, Stock goog, Stock tsla) {
        if (whichStock.equalsIgnoreCase("aapl")) {
            return aapl;
        } else if (whichStock.equalsIgnoreCase("fb")) {
            return fb;
        } else if (whichStock.equalsIgnoreCase("goog")) {
            return goog;
        }
        return tsla;
    }

    public static boolean buyOrSell(String buyOrSell) {
        if (buyOrSell.equalsIgnoreCase("buy")) {
            return true;
        } else if (buyOrSell.equalsIgnoreCase("sell")) {
            return true;
        }
        return false;
    }
}