import java.util.*;

class MainDemo {
    private Map<String, Double> stockrate;
    private Map<String, Integer> portfolio;

    public MainDemo() {
        stockrate = new HashMap<>();
        stockrate.put("Meta", 180.25);
        stockrate.put("Google", 2600.75);
        stockrate.put("Amazon", 380.50);
        stockrate.put("CodeAlfha",2024.24);
        
        portfolio = new HashMap<>();
    }
    //for displaying the stockrate.
    public void displayStockRate() {
        System.out.println("********** Stock Prices *********");
        for (String symbol : stockrate.keySet()) {
            System.out.println(symbol + " : $" + stockrate.get(symbol));
        }
    }
    //for buying the stocks.
    public void buyStock(String symbol, int quantity) {
        double price = stockrate.get(symbol);
        if (price == 0) {
            System.out.println("Stock symbol not found");
        } else {
            double totalCost = price * quantity;
            if (totalCost > CashAccount.balance) {
                System.out.println("Insufficient funds");
            } else {
                CashAccount.balance -= totalCost;
                if (portfolio.containsKey(symbol)) {
                    portfolio.put(symbol, portfolio.get(symbol) + quantity);
                } else {
                    portfolio.put(symbol, quantity);
                }
                System.out.println("Stock purchased successfully");
            }
        }
    }
    //for selling the stocks.
    public void sellStock(String symbol, int quantity) {
        double price = stockrate.get(symbol);
        if (price == 0) {
            System.out.println("Stock symbol not found");
        } else {
            if (portfolio.containsKey(symbol)) {
                int currentQuantity = portfolio.get(symbol);
                if (quantity > currentQuantity) {
                    System.out.println("Not enough shares to sell");
                } else {
                    double totalValue = price * quantity;
                    CashAccount.balance += totalValue;
                    portfolio.put(symbol, currentQuantity - quantity);
                    System.out.println("Stock sold successfully");
                }
            } else {
                System.out.println("You do not own any shares of this stock");
            }
        }
    }
    //for displaying portfolio
    public void displayPortfolio() {
        System.out.println("===== Portfolio =====");
        for (String symbol : portfolio.keySet()) {
            System.out.println(symbol + " : " + portfolio.get(symbol));
        }
    }

    public static void main(String[] args) {
        MainDemo m1 = new MainDemo();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("****** Stock Trading Platform Menu *******");
            System.out.println("1. Display Stock Prices");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Display Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            //using switch case statement for choosing the desired input.
            switch (choice) {
                case 1:
                    m1.displayStockRate();
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String symbol = sc.next();
                    
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    
                    m1.buyStock(symbol, quantity);
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    symbol = sc.next();
                    
                    System.out.print("Enter quantity: ");
                    quantity = sc.nextInt();
                    
                    m1.sellStock(symbol, quantity);
                    break;
                case 4:
                    m1.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exit Program,Thanku for using.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

//class made for creating balance.
class CashAccount {
    public static double balance = 10000.0;
}

