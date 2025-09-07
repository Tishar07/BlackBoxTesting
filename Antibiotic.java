public class Antibiotic extends Medicine {
    private String spectrum;
    private String resistanceRisk;

    public Antibiotic(String medType, String medName, String dosage, String manufacturer, String expiryDate, String quantity, String spectrum, String resistanceRisk) {
        super(medType, medName, dosage, manufacturer, expiryDate, quantity);
        this.spectrum = spectrum;
        this.resistanceRisk = resistanceRisk;
    }

    // Getters and Setters
    public String getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(String spectrum) {
        this.spectrum = spectrum;
    }

    public String getResistanceRisk() {
        return resistanceRisk;
    }

    public void setResistanceRisk(String resistanceRisk) {
        this.resistanceRisk = resistanceRisk;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Spectrum: " + spectrum);
        System.out.println("Resistance risk: " + resistanceRisk);
    }
}