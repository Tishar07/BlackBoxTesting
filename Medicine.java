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

    // Setters
    public void setMedName(String medName) {
        this.MedName = medName;
    }

    public void setMedType(String medType) {
        this.MedType = medType;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
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
        if (value > 0) {
            this.quantity += value;
        } else {
            System.out.println(value + " units cannot be added");
        }
    }

    public void dispenseQuantity(int value) {
        if (value > this.quantity) {
            System.out.println("Amount to dispense exceeds availability; only can dispense " + this.quantity + " units");
            System.out.print("Do you want to dispense maximum available ?[Y/N] : ");
        
        // Use try-with-resources to ensure the scanner is closed
            try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            String userIn = scanner.nextLine();
                if (userIn.equalsIgnoreCase("Y")) {
                    this.quantity = 0;
                    System.out.println("Dispensed all available units. Remaining: 0");
                } else {
                System.out.println("Dispense operation terminated!");
            }
        }
        } else {
        this.quantity -= value;
    }
}
}