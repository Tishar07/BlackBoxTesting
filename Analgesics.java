public class Analgesics extends Medicine {
    private String painType;
    private String opioid;

    public Analgesics(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String painType, String opioid) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.painType = painType;
        this.opioid = opioid;
    }

    // Getters and Setters
    public String getPainType() {
        return painType;
    }

    public void setPainType(String painType) {
        this.painType = painType;
    }

    public String getOpioid() {
        return opioid;
    }

    public void setOpioid(String opioid) {
        this.opioid = opioid;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Pain type: " + painType);
        System.out.println("Opioid: " + opioid);
    }
}
