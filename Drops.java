public class Drops extends Medicine {
    private String applicationArea;
    private String preservationFree;

    public Drops(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String applicationArea, String preservationFree) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.applicationArea = applicationArea;
        this.preservationFree = preservationFree;
    }

    // Getters and Setters
    public String getApplicationArea() {
        return applicationArea;
    }

    public void setApplicationArea(String applicationArea) {
        this.applicationArea = applicationArea;
    }

    public String getPreservationFree() {
        return preservationFree;
    }

    public void setPreservationFree(String preservationFree) {
        this.preservationFree = preservationFree;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Application area: " + applicationArea);
        System.out.println("Preservation free: " + preservationFree);
    }
}