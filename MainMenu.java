package MedicationTracking;

import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        boolean exit = false;

        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("=====Welcome To The Pharmacy Med Tracking System=====");
            System.out.println("What would you like to do? ");
            System.out.println("1: Add/Delete A Patient");
            System.out.println("2: Add/Delete A Doctor");
            System.out.println("3: Add/Delete A Medication");
            System.out.println("4: Print System Report");
            System.out.println("5: Check If Meds Are Expired");
            System.out.println("6: Process A New Prescription");
            System.out.println("7: Print All Scripts For Specific Doctor");
            System.out.println("8: Restock the drugs in the pharmacy");
            System.out.println("9: Print All Scripts For Specific Patient");
            System.out.println("10: Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> managePatient(scanner, system);
                case 2 -> manageDoctor(scanner, system);
                case 3 -> manageMedication(scanner, system);
                case 4 -> printPharmacyReport(system);
                case 5 -> checkExpiredMeds(system);
                case 6 -> processANewScript(scanner, system);
                case 7 -> printScriptsForSpecificDoctor(scanner, system);
                case 8 -> restockPharmacyDrugs(scanner, system);
                case 9 -> printAllScriptsForPatientByName(scanner, system);
                case 10 -> {
                    exit = true;
                    System.out.println("Exiting The System! Good Bye!");
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void managePatient(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("1: Add A New Patient");
        System.out.println("2: Delete A Patient");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addANewPatient(scanner, system);
            case 2 -> deletePatient(scanner, system);
            default -> System.out.println("Invalid option");
        }
    }

    private static void manageDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("1: Add A New Doctor");
        System.out.println("2: Delete A Doctor");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addANewDoctor(scanner, system);
            case 2 -> deleteDoctor(scanner, system);
            default -> System.out.println("Invalid option");
        }
    }

    private static void manageMedication(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("1: Add A New Medication");
        System.out.println("2: Delete A Medication");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addNewMedicationToPharmacy(scanner, system);
            case 2 -> deleteMedication(scanner, system);
            default -> System.out.println("Invalid option");
        }
    }

    private static void addANewPatient(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Patient ID:");
        String id = scanner.next();
        System.out.println("Enter Patient First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Patient Last Name:");
        String lastName = scanner.next();
        String name = firstName + " " + lastName; // Combine first name and last name
        System.out.println("Enter Patient Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Patient Phone Number:");
        String phoneNumber = scanner.next();
        Patient patient = new Patient(id, name, age, phoneNumber);
        system.addPatient(patient);
        System.out.println("Patient added successfully!");
    }

    private static void deletePatient(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Patient ID to delete:");
        String id = scanner.next();
        system.deletePatient(id);
        System.out.println("Patient deleted successfully!");
    }

    private static void addANewDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Doctor ID:");
        String id = scanner.next();
        System.out.println("Enter Doctor Name:");
        String name = scanner.next();
        System.out.println("Enter Doctor Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Doctor Phone Number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter Doctor Specialization:");
        String specialization = scanner.next();
        Doctor doctor = new Doctor(id, name, age, phoneNumber, specialization);
        system.addDoctor(doctor);
        System.out.println("Doctor added successfully!");
    }

    private static void deleteDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Doctor ID to delete:");
        String id = scanner.next();
        system.deleteDoctor(id);
        System.out.println("Doctor deleted successfully!");
    }

    private static void addNewMedicationToPharmacy(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Medication ID:");
        String id = scanner.next();
        System.out.println("Enter Medication Name:");
        String name = scanner.next();
        System.out.println("Enter Medication Dose:");
        String dose = scanner.next();
        System.out.println("Enter Quantity in Stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Expiry Date (yyyy-mm-dd):");
        String expiryDateStr = scanner.next();
        Date expiryDate = java.sql.Date.valueOf(expiryDateStr);
        Medication medication = new Medication(id, name, dose, quantityInStock, expiryDate);
        system.addMedication(medication);
        System.out.println("Medication added successfully!");
    }

    private static void deleteMedication(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Medication ID to delete:");
        String id = scanner.next();
        system.deleteMedication(id);
        System.out.println("Medication deleted successfully!");
    }

    private static void printPharmacyReport(MedicationTrackingSystem system) {
        System.out.println("Pharmacy Report:");
        // Example implementation for printing the system report
        for (Patient patient : system.getPatients()) {
            System.out.println("Patient ID: " + patient.getId() + ", Name: " + patient.getName());
        }
        for (Doctor doctor : system.getDoctors()) {
            System.out.println("Doctor ID: " + doctor.getId() + ", Name: " + doctor.getName());
        }
        for (Medication medication : system.getMedications()) {
            System.out.println("Medication ID: " + medication.getId() + ", Name: " + medication.getName());
        }
        for (Prescription prescription : system.getPrescriptions()) {
            System.out.println("Prescription ID: " + prescription.getId() + ", Medication: " + prescription.getMedication().getName());
        }
    }

    private static void checkExpiredMeds(MedicationTrackingSystem system) {
        System.out.println("Checking for expired medications...");
        // Example implementation for checking expired medications
        for (Medication medication : system.getMedications()) {
            if (medication.getExpiryDate().before(new Date())) {
                System.out.println("Expired Medication: " + medication.getName());
            }
        }
    }

    private static void processANewScript(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Prescription ID:");
        String id = scanner.next();
        System.out.println("Enter Doctor ID:");
        String doctorId = scanner.next();
        System.out.println("Enter Patient ID:");
        String patientId = scanner.next();
        System.out.println("Enter Medication ID:");
        String medicationId = scanner.next();
        System.out.println("Enter Prescription Expiry Date (yyyy-mm-dd):");
        String expiryDateStr = scanner.next();
        Date prescriptionExpiry = java.sql.Date.valueOf(expiryDateStr);
        // Find doctor, patient, and medication by ID
        Doctor doctor = system.findDoctorById(doctorId);
        Patient patient = system.findPatientById(patientId);
        Medication medication = system.findMedicationById(medicationId);
        Prescription prescription = new Prescription(id, doctor, patient, medication, prescriptionExpiry);
        system.addPrescription(prescription);
        System.out.println("Prescription processed successfully!");
    }

    private static void printScriptsForSpecificDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Doctor ID:");
        String doctorId = scanner.next();
        // Example implementation for printing all prescriptions for a specific doctor
        Doctor doctor = system.findDoctorById(doctorId);
        if (doctor != null) {
            for (Prescription prescription : system.getPrescriptions()) {
                if (prescription.getDoctor().equals(doctor)) {
                    System.out.println("Prescription ID: " + prescription.getId() + ", Medication: " + prescription.getMedication().getName());
                }
            }
        } else {
            System.out.println("Doctor not found.");
        }
    }
    
    private static void restockPharmacyDrugs(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Restocking drugs in the pharmacy...");
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Wait for the user to press Enter
    
        // Example implementation for restocking drugs in the pharmacy
        for (Medication medication : system.getMedications()) {
            int restockAmount = (int) (Math.random() * 100); // Random restock amount
            medication.setQuantityInStock(medication.getQuantityInStock() + restockAmount);
            System.out.println("Restocked " + restockAmount + " units of " + medication.getName());
        }
    }
    
    private static void printAllScriptsForPatientByName(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Enter Patient Name:");
        String patientName = scanner.next();
        // Example implementation for printing all prescriptions for a specific patient
        for (Patient patient : system.getPatients()) {
            if (patient.getName().equalsIgnoreCase(patientName)) {
                for (Prescription prescription : system.getPrescriptions()) {
                    if (prescription.getPatient().equals(patient)) {
                        System.out.println("Prescription ID: " + prescription.getId() + ", Medication: " + prescription.getMedication().getName());
                    }
                }
            }
        }
    }
}