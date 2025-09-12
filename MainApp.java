import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);

public static void uiAddNewMedicine() {
    System.out.println("Enter the details for the new medicine");

    System.out.print("Enter Medicine Type: ");
    String medType = scanner.nextLine().trim().toLowerCase();
    if (!(medType.equalsIgnoreCase("analgesics") || medType.equalsIgnoreCase("antibiotic") ||
          medType.equalsIgnoreCase("drops") || medType.equalsIgnoreCase("inhaler") ||
          medType.equalsIgnoreCase("injection") || medType.equalsIgnoreCase("overthecounter"))) {
            System.out.println("Invalid Type !");
            return;
          }


    // Medicine Name
    System.out.print("Enter Medicine Name: ");
    String medName = scanner.nextLine().trim();
    if (!(medName.matches("[a-zA-Z]+"))){
        System.out.println("Invalid Input(Must be Alphabetic)");
        return;
    }
    if (medName.isEmpty() || medName.length() > 25) {
        System.out.println("Invalid Medicine name: must be 1-25 characters long");
        return;
    }
    

    // Dosage
    System.out.print("Enter Dosage: ");
    String dosage = scanner.nextLine().trim();
   
    
    if (dosage.isEmpty()) {
        System.out.println("Invalid Dosage: cannot be empty");
        return;
    }
    if (!(dosage.matches("^\\d+\\s*[a-zA-Z]+$"))) {
        System.out.println("Dosage must always start with integer value then metric !");
        return;
    }    
     Integer numberPart = Integer.parseInt(dosage.replaceAll("[^0-9]", "").trim());
    if(numberPart >2000 || numberPart < 1){
        System.out.println("Invalid Dosage amount(1-2000)");
        return;
    }


    // Manufacturer
    System.out.print("Enter Manufacturer: ");
    String manufacturer = scanner.nextLine().trim();
    if (manufacturer.isEmpty() || manufacturer.length() > 20) {
        System.out.println("Invalid Manufacturer: must be 1-20 characters long");
        return;
    }
    if (!(manufacturer.matches("[a-zA-Z+]"))){
        System.out.println("Invalid type , Alphabetics Only !");
    }

    // Expiry Date
    System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
    String expDate = scanner.nextLine().trim();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    try {
        LocalDate.parse(expDate, formatter);
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Use yyyy-MM-dd.");
        return;
    }

    // Quantity
    System.out.print("Enter Quantity: ");
    String quantityStr = scanner.nextLine().trim();
    int quantity;
    
    try {
        quantity = Integer.parseInt(quantityStr);
        if (quantity <= 0 || quantity > 10000) {
            System.out.println("Invalid Quantity: must be between 1 and 10000");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid Quantity: not a valid integer");
        return;
    }

    String specific1, specific2;
    Medicine newMed;

    switch (medType.toLowerCase()) {
        case "antibiotic":
        case "analgesics":
        case "drops":
        case "injection":
            // Specific1
            System.out.print("Enter specific1 (alphabetic only): ");
            specific1 = scanner.nextLine().trim();
            if (!(specific1.matches("[a-zA-Z]+"))) {
                System.out.println("Invalid input: specific 1 must be alphabetic only");
                return;
            }

            // Specific2
            System.out.print("Enter specific2 (alphabetic only): ");
            specific2 = scanner.nextLine().trim();
            if (!(specific2.matches("[a-zA-Z]+"))) {
                System.out.println("Invalid input: specific 2 must be alphabetic only");
                return;
            }

            newMed = createNewMed(medType, medName, dosage, manufacturer, expDate, String.valueOf(quantity), specific1, specific2);
            break;

        case "inhaler":
        case "overthecounter":
            System.out.print("Enter specific1 (alphabetic only): ");
            specific1 = scanner.nextLine().trim();
            if (!(specific1.matches("[a-zA-Z]+"))) {
                System.out.println("Invalid input: specific 1 must be alphabetic only");
                return;
            }
            newMed = createNewMed(medType, medName, dosage, manufacturer, expDate, String.valueOf(quantity), specific1);
            break;

        default:
            System.out.println("Invalid medicine type.");
            return;
    }

    Inventory.addMedToList(newMed);
    System.out.println("Medicine added successfully!");
}



    private static Medicine createNewMed(String medType, String medName, String dosage, String manufacturer, String expDate, String quantity, String... extra) {
        switch (medType.toLowerCase()) {
            case "antibiotic":
                return new Antibiotic("Antibiotic", medName, dosage, manufacturer, expDate, quantity, extra[0], extra[1]);
            case "analgesics":
                return new Analgesics("Analgesics", medName, dosage, manufacturer, expDate, quantity, extra[0], extra[1]);
            case "drops":
                return new Drops("Drops", medName, dosage, manufacturer, expDate, quantity, extra[0], extra[1]);
            case "injection":
                return new Injection("Injection", medName, dosage, manufacturer, expDate, quantity, extra[0], extra[1]);
            case "inhaler":
                return new Inhaler("Inhaler", medName, dosage, manufacturer, expDate, quantity, extra[0]);
            case "overthecounter":
                return new OverTheCounter("OverTheCounter", medName, dosage, manufacturer, expDate, quantity, extra[0]);
            default:
                return null;
        }
    }

    public static void mainDisplayMenu() {
        String menuText = """
                [1]: Display Inventory List
                [2]: Search for Medicine Availability
                [3]: Dispense Medicine units
                [4]: Add Medicine units
                [5]: Add new Medicine Details
                [0]: Exit System
                """;

        int userIn = 99;
        while (userIn != 0) {
            System.out.println(menuText);
            try {
                System.out.print("Select Option: ");
                userIn = scanner.nextInt();
                scanner.nextLine(); 

                switch (userIn) {
                    case 1:
                        Inventory.displayInventory();
                        break;
                    case 2:
                        System.out.print("Enter Medicine to search: ");
                        String searchMedName = scanner.nextLine().trim();
                        int searchIndex = Inventory.searchMed(searchMedName);
                        if (searchIndex ==0 ){
                            break;
                        }
                        if (searchIndex == -1) {
                            System.out.println("The medicine does not exist in the inventory.");
                        } else {
                            System.out.println();
                            Inventory.MedList.get(searchIndex).displayInfo();
                        }
                        break;
                    case 3:
                        System.out.print("Enter Medicine to Dispense: ");
                        String dispenseMedName = scanner.nextLine().trim();
                        int dispenseIndex = Inventory.searchMed(dispenseMedName);
                        if (dispenseIndex ==0 ){
                            break;
                        }
                        if (dispenseIndex == -1) {
                            System.out.println("The medicine does not exist in the inventory.");
                        } else {
                            Medicine med = Inventory.MedList.get(dispenseIndex);
                            if (med.getQuantity()==0){
                                System.out.println("No units to dispense !");
                                break;
                            }
                            System.out.println("Available : " + med.getQuantity() + " units");
                            System.out.print("Enter amount to dispense: ");
                            int amountToDispense = scanner.nextInt();
                            scanner.nextLine();
                            med.dispenseQuantity(amountToDispense);
                            System.out.println("Updated after dispense units : " + med.getQuantity() + " units");   

                            
                        }
                        break;
                    case 4:
                        System.out.print("Enter Medicine to Add up: ");
                        String addMedName = scanner.nextLine().trim();
                        int addIndex = Inventory.searchMed(addMedName);
                        if (addIndex == -1) {
                            System.out.println("Medicine not found");
                        } else {
                            Medicine med = Inventory.MedList.get(addIndex);
                            System.out.println("Available : " + med.getQuantity() + " units");
                            System.out.print("Enter Amount to Add: ");
                            int amountToAdd = scanner.nextInt();
                            scanner.nextLine();
                            med.addQuantity(amountToAdd);
                            System.out.println("Available : " + med.getQuantity() + " units");
                        }
                        break;
                    case 5:
                        uiAddNewMedicine();
                        break;
                    case 0:
                        Inventory.saveInventory("MedicineInventory.txt");
                        System.out.println("System Terminated and all changes are saved");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                userIn = 99; 
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Inventory.readInsertMedicine("MedicineInventory.txt");
        mainDisplayMenu();
    }
}