package MedicationTracking;

import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        boolean exit = false;
    
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
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
                scanner.nextLine(); // Consume the newline character
                switch (option) {
                    case 1 -> managePatient(scanner, system);
                    case 2 -> manageDoctor(scanner, system);
                    case 3 -> manageMedication(scanner, system);
                    case 4 -> printPharmacyReport(system);
                    case 5 -> checkExpiredMeds(system);
                    case 6 -> processANewScript(scanner, system);
                    case 7 -> printScriptsForSpecificDoctor(scanner, system);
                    case 8 -> restockPharmacyDrugs(scanner, system);
                    case 9 -> printAllScriptsForPatient(scanner, system);
                    case 10 -> {
                        exit = true;
                        System.out.println("Exiting The System! Good Bye!");
                    }
                    default -> System.out.println("Invalid option");
                }
                System.out.println(); // Add a blank line for spacing
            }
        }
    }

    private static void managePatient(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("1: Add A New Patient");
            System.out.println("2: Delete A Patient");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1 -> addANewPatient(scanner, system);
                case 2 -> deletePatient(scanner, system);
                default -> System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void manageDoctor(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("1: Add A New Doctor");
            System.out.println("2: Delete A Doctor");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1 -> addANewDoctor(scanner, system);
                case 2 -> deleteDoctor(scanner, system);
                default -> System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void manageMedication(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("1: Add A New Medication");
        System.out.println("2: Delete A Medication");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (choice) {
            case 1 -> addNewMedicationToPharmacy(scanner, system);
            case 2 -> deleteMedication(scanner, system);
            default -> System.out.println("Invalid option");
        }
    }

    private static void addANewPatient(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Patient ID:");
            String id = scanner.next();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Patient First Name:");
            String firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid first name. Please enter alphabetic characters only.");
            }

            System.out.println("Enter Patient Last Name:");
            String lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid last name. Please enter alphabetic characters only.");
            }

            String name = firstName + " " + lastName; // Combine first name and last name

            System.out.println("Enter Patient Age:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Patient Phone Number:");
            String phoneNumber = scanner.next();
            scanner.nextLine(); // Consume the newline character

            Patient patient = new Patient(id, name, age, phoneNumber);
            system.addPatient(patient);
            System.out.println("Patient added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private static void deletePatient(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Patient ID or Name to delete:");
            String input = scanner.nextLine();

            boolean deleted = false;

            // Check if the input is a numeric ID
            if (input.matches("\\d+")) {
                deleted = system.deletePatientById(input);
            }

            // If not deleted by ID, try to delete by name
            if (!deleted) {
                deleted = system.deletePatientByName(input);
            }

            if (deleted) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Patient not found!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void addANewDoctor(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Doctor ID:");
            String id = scanner.next();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Doctor First Name:");
            String firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid first name. Please enter alphabetic characters only.");
            }

            System.out.println("Enter Doctor Last Name:");
            String lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid last name. Please enter alphabetic characters only.");
            }

            String name = firstName + " " + lastName; // Combine first name and last name

            System.out.println("Enter Doctor Age:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Doctor Phone Number:");
            String phoneNumber = scanner.next();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Doctor Specialization:");
            String specialization = scanner.nextLine();
            if (specialization.trim().isEmpty()) {
                throw new IllegalArgumentException("Specialization cannot be empty.");
            }

            Doctor doctor = new Doctor(id, name, age, phoneNumber, specialization);
            system.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private static void deleteDoctor(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Doctor ID or Name to delete:");
            String input = scanner.nextLine();

            boolean deleted = false;

            // Check if the input is a numeric ID
            if (input.matches("\\d+")) {
                deleted = system.deleteDoctorById(input);
            }

            // If not deleted by ID, try to delete by name
            if (!deleted) {
                deleted = system.deleteDoctorByName(input);
            }

            if (deleted) {
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Doctor not found!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void addNewMedicationToPharmacy(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Medication ID:");
            String id = scanner.next();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Medication Name:");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Medication name cannot be empty.");
            }

            System.out.println("Enter Medication Dose:");
            String dose = scanner.nextLine();
            if (dose.trim().isEmpty()) {
                throw new IllegalArgumentException("Medication dose cannot be empty.");
            }

            System.out.println("Enter Quantity in Stock:");
            int quantityInStock = scanner.nextInt();
            if (quantityInStock < 0) {
                throw new IllegalArgumentException("Quantity in stock cannot be negative.");
            }
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter Expiry Date (yyyy-mm-dd):");
            String expiryDateStr = scanner.next();
            Date expiryDate = java.sql.Date.valueOf(expiryDateStr);

            Medication medication = new Medication(id, name, dose, quantityInStock, expiryDate);
            system.addMedication(medication);
            System.out.println("Medication added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void deleteMedication(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Medication ID or Name to delete:");
            String input = scanner.nextLine();

            boolean deletedById = false;
            boolean deletedByName = false;

            // Check if the input is a numeric ID
            if (input.matches("\\d+")) {
                deletedById = system.deleteMedicationById(input);
            }

            // If not deleted by ID, try to delete by name
            if (!deletedById) {
                deletedByName = system.deleteMedicationByName(input);
            }

            if (deletedById || deletedByName) {
                System.out.println("Medication deleted successfully!");
            } else {
                System.out.println("No medication found with the given ID or name.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void printPharmacyReport(MedicationTrackingSystem system) {
        System.out.println("===== Pharmacy Report =====");
    
        // Print Patients
        System.out.println("\nPatients:");
        System.out.println("=========================");
        for (Patient patient : system.getPatients()) {
            System.out.printf("ID: %s\nName: %s\nAge: %d\nPhone: %s%n%n",
                    patient.getId(), patient.getName(), patient.getAge(), patient.getPhoneNumber());
        }
    
        // Print Doctors
        System.out.println("Doctors:");
        System.out.println("=========================");
        for (Doctor doctor : system.getDoctors()) {
            System.out.printf("ID: %s\nName: %s\nAge: %d\nPhone: %s\nSpecialization: %s%n%n",
                    doctor.getId(), doctor.getName(), doctor.getAge(), doctor.getPhoneNumber(), doctor.getSpecialization());
        }
    
        // Print Medications
        System.out.println("Medications:");
        System.out.println("=========================");
        for (Medication medication : system.getMedications()) {
            System.out.printf("ID: %s\nName: %s\nDose: %s\nQuantity: %d\nExpiry: %s%n%n",
                    medication.getId(), medication.getName(), medication.getDose(),
                    medication.getQuantityInStock(), medication.getExpiryDate());
        }
    
        // Print Prescriptions
        System.out.println("Prescriptions:");
        System.out.println("=========================");
        for (Prescription prescription : system.getPrescriptions()) {
            System.out.printf("ID: %s\nDoctor: %s\nPatient: %s\nMedication: %s\nExpiry: %s%n%n",
                    prescription.getId(), prescription.getDoctor().getName(),
                    prescription.getPatient().getName(), prescription.getMedication().getName(),
                    prescription.getPrescriptionExpiry());
        }
    }

    private static void checkExpiredMeds(MedicationTrackingSystem system) {
        System.out.println("===== Checking for Expired Medications =====");
        boolean foundExpired = false;
        for (Medication medication : system.getMedications()) {
            if (medication.getExpiryDate().before(new Date())) {
                System.out.printf("Expired Medication: %s (ID: %s)\nExpiry Date: %s%n%n",
                        medication.getName(), medication.getId(), medication.getExpiryDate());
                foundExpired = true;
            }
        }
        if (!foundExpired) {
            System.out.println("No expired medications found.");
        }
    }

    private static void processANewScript(Scanner scanner, MedicationTrackingSystem system) {
        try {
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

            if (doctor != null && patient != null && medication != null) {
                Prescription prescription = new Prescription(id, doctor, patient, medication, prescriptionExpiry);
                system.addPrescription(prescription);
                System.out.println("Prescription processed successfully!");
            } else {
                System.out.println("Invalid doctor, patient, or medication ID.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void printScriptsForSpecificDoctor(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Doctor Name or ID:");
            String input = scanner.nextLine();
    
            Doctor doctor = input.matches("\\d+") ? system.findDoctorById(input) : system.findDoctorByName(input);
    
            if (doctor != null) {
                System.out.println("===== Prescriptions for Doctor: " + doctor.getName() + " =====");
                for (Prescription prescription : system.getPrescriptions()) {
                    if (prescription.getDoctor().equals(doctor)) {
                        System.out.printf("Prescription ID: %s\nMedication: %s\nPatient: %s\nExpiry: %s%n%n",
                                prescription.getId(), prescription.getMedication().getName(),
                                prescription.getPatient().getName(), prescription.getPrescriptionExpiry());
                    }
                }
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
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

    private static void printAllScriptsForPatient(Scanner scanner, MedicationTrackingSystem system) {
        try {
            System.out.println("Enter Patient Name or ID:");
            String input = scanner.nextLine();
    
            Patient patient = input.matches("\\d+") ? system.findPatientById(input) : system.findPatientByName(input);
    
            if (patient != null) {
                System.out.println("===== Prescriptions for Patient: " + patient.getName() + " =====");
                for (Prescription prescription : system.getPrescriptions()) {
                    if (prescription.getPatient().equals(patient)) {
                        System.out.printf("Prescription ID: %s\nMedication: %s\nDoctor: %s\nExpiry: %s%n%n",
                                prescription.getId(), prescription.getMedication().getName(),
                                prescription.getDoctor().getName(), prescription.getPrescriptionExpiry());
                    }
                }
            } else {
                System.out.println("Patient not found!");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
            scanner.nextLine(); // Clear the invalid input
        }
    }
}