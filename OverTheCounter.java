public class OverTheCounter extends Medicine {
    private String commonUse;

    public OverTheCounter(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String commonUse) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.commonUse = commonUse;
    }

    // Getters and Setters
    public String getCommonUse() {
        return commonUse;
    }

    public void setCommonUse(String commonUse) {
        this.commonUse = commonUse;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Common use: " + commonUse);
    }
}