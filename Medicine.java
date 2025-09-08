import java.io.Serializable;


public class Medicine implements Serializable {
    protected String MedName;
    protected String MedType;
    protected String dosage;
    protected String manufacturer;
    protected String expiryDate;
    protected int quantity;

    public Medicine(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity) {
        this.MedType = medType;
        this.MedName = medName;
        this.dosage = dosage;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.quantity = Integer.parseInt(quantity);
    }

    // Getters
    public String getMedName() {
        return MedName;
    }

    public String getMedType() {
        return MedType;
    }

    public String getDosage() {
        return dosage;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }


    public void displayInfo() {
        System.out.println("Medicine Details");
        System.out.println("Name: " + MedName);
        System.out.println("Type: " + MedType);
        System.out.println("Dosage: " + dosage);
        System.out.println("Quantity: " + quantity);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Expiry date: " + expiryDate);
    }

    public void addQuantity(int value) {
        if (value >= 50 && value <= 1000) {
            this.quantity += value;
        } else if( value > 1000) {
            System.out.println("Value to add cannot Exceed 1000 !");
        }else if (value < 50){
            System.out.println("Value to add cannot be less than 50 !");
        }else if( value <0){
            System.out.println("Negative Value not allowed");
        }else{
            System.out.println("Invalid Input , Enter A whole Number !");
        }
    }

    public void dispenseQuantity(int value) {
        if (value > this.quantity && this.quantity !=0)  {
            System.out.println("Amount to dispense exceeds availability; only can dispense " + this.quantity + " units");
            System.out.print("Do you want to dispense maximum available ?[Y/N] : ");
            try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
                String userIn = scanner.nextLine();
                if (userIn.equalsIgnoreCase("Y")) {
                    this.quantity = 0;
                    System.out.println("Dispensed all available units. Remaining: 0");
                } else {
                System.out.println("Dispense operation terminated!");
                }
            }
        } else if(value <0) {
            System.out.println("Value to dispense cannot be Negative !");
        }else{
        this.quantity -= value;
        }
    
    
}
}