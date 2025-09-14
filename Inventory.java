import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    public static List<Medicine> MedList = new ArrayList<>();

    public static void addMedToList(Medicine medicine) {
        MedList.add(medicine);
    }

    public static void displayInventory() {
        String[] header = {"Medicine Name", "Medicine Type", "Dosage", "Manufacturer", "Expiry Date", "Quantity Available", "Specific 1", "Specific 2"};
        int colWidth = 20;

        // Print header
        System.out.printf("%-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s\n", (Object[]) header);
        System.out.println("-".repeat(colWidth * header.length));

        // Loop through the medicine list and print details
        for (Medicine med : MedList) {
            String specific1 = "", specific2 = "";
            if (med instanceof Antibiotic) {
                Antibiotic a = (Antibiotic) med;
                specific1 = a.getSpectrum();
                specific2 = a.getResistanceRisk();
            } else if (med instanceof Analgesics) {
                Analgesics a = (Analgesics) med;
                specific1 = a.getPainType();
                specific2 = a.getOpioid();
            } else if (med instanceof Drops) {
                Drops d = (Drops) med;
                specific1 = d.getApplicationArea();
                specific2 = d.getPreservationFree();
            } else if (med instanceof Injection) {
                Injection i = (Injection) med;
                specific1 = i.getInjectionType();
                specific2 = i.getAbsorptionSpeed();
            } else if (med instanceof Inhaler) {
                Inhaler i = (Inhaler) med;
                specific1 = i.getInhalerType();
            } else if (med instanceof OverTheCounter) {
                OverTheCounter o = (OverTheCounter) med;
                specific1 = o.getCommonUse();
            }

            System.out.printf("%-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s %-" + colWidth + "s\n",
                    med.getMedName(), med.getMedType(), med.getDosage(), med.getManufacturer(), med.getExpiryDate(),
                    med.getQuantity(), specific1, specific2);
        }
    }
    public static void readInsertMedicine(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Add a check to skip empty or blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\*|\\?");
                List<String> medLine = new ArrayList<>(Arrays.asList(parts));
                medLine.removeIf(String::isEmpty);

                Medicine newMed = createNewMed(medLine.toArray(new String[0]));
                MedList.add(newMed);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static Medicine createNewMed(String[] medLine) {
        String medType = medLine[0].toLowerCase();
        switch (medType) {
            case "antibiotic":
                return new Antibiotic(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6], medLine[7]);
            case "analgesics":
                return new Analgesics(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6], medLine[7]);
            case "drops":
                return new Drops(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6], medLine[7]);
            case "injection":
                return new Injection(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6], medLine[7]);
            case "inhaler":
                return new Inhaler(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6]);
            case "overthecounter":
                return new OverTheCounter(medLine[0], medLine[1], medLine[2], medLine[3], medLine[4], medLine[5], medLine[6]);
            default:
                throw new IllegalArgumentException("Unknown medicine type: " + medType);
        }
    }

    public static void saveInventory(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Medicine med : MedList) {
                String line = formatToFile(med);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    private static String formatToFile(Medicine med) {
        StringBuilder sb = new StringBuilder();
        sb.append(med.getMedType()).append("*")
          .append(med.getMedName()).append("*")
          .append(med.getDosage()).append("*")
          .append(med.getManufacturer()).append("*")
          .append(med.getExpiryDate()).append("*")
          .append(med.getQuantity());

        if (med instanceof Antibiotic) {
            Antibiotic a = (Antibiotic) med;
            sb.append("?").append(a.getSpectrum()).append("?").append(a.getResistanceRisk()).append("?");
        } else if (med instanceof Analgesics) {
            Analgesics a = (Analgesics) med;
            sb.append("?").append(a.getPainType()).append("?").append(a.getOpioid()).append("?");
        } else if (med instanceof Drops) {
            Drops d = (Drops) med;
            sb.append("?").append(d.getApplicationArea()).append("?").append(d.getPreservationFree()).append("?");
        } else if (med instanceof Injection) {
            Injection i = (Injection) med;
            sb.append("?").append(i.getInjectionType()).append("?").append(i.getAbsorptionSpeed()).append("?");
        } else if (med instanceof Inhaler) {
            Inhaler i = (Inhaler) med;
            sb.append("?").append(i.getInhalerType()).append("?");
        } else if (med instanceof OverTheCounter) {
            OverTheCounter o = (OverTheCounter) med;
            sb.append("?").append(o.getCommonUse()).append("?");
        }
        return sb.toString();
    }

    public static int searchMed(String medName) {
        if (medName.isEmpty() || medName.length() > 25) {
            System.out.println("Invalid Medicine name: must be 1-25 characters long");
            return 0; 
        }
        for (int i = 0; i < MedList.size(); i++) {
            if (MedList.get(i).getMedName().equalsIgnoreCase(medName)) {
                return i; 
            }
        }
        if (!medName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input (must be alphabetic)");
            return 0; 
        }
        return -1; 
    }

}