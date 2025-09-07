public class Injection extends Medicine {
    private String injectionType;
    private String absorptionSpeed;

    public Injection(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String injectionType, String absorptionSpeed) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.injectionType = injectionType;
        this.absorptionSpeed = absorptionSpeed;
    }

    // Getters and Setters
    public String getInjectionType() {
        return injectionType;
    }

    public void setInjectionType(String injectionType) {
        this.injectionType = injectionType;
    }

    public String getAbsorptionSpeed() {
        return absorptionSpeed;
    }

    public void setAbsorptionSpeed(String absorptionSpeed) {
        this.absorptionSpeed = absorptionSpeed;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Injection Type: " + injectionType);
        System.out.println("Absorption speed: " + absorptionSpeed);
    }
}