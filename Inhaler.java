public class Inhaler extends Medicine {
    private String inhalerType;

    public Inhaler(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String inhalerType) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.inhalerType = inhalerType;
    }

    // Getters and Setters
    public String getInhalerType() {
        return inhalerType;
    }

    public void setInhalerType(String inhalerType) {
        this.inhalerType = inhalerType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Inhaler type: " + inhalerType);
    }
}