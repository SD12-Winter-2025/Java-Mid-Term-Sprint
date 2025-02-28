package MedicationTracking;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MedicationTrackingSystem {
    private final List<Patient> patients;
    private final List<Doctor> doctors;
    private final List<Medication> medications;
    private final List<Prescription> prescriptions;

    public MedicationTrackingSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // Methods to add, edit, delete, and search for patients, doctors, and medications
    // Methods to process prescriptions, check for expired medications, generate reports, etc.

    // Example method to add a new patient
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Example method to add a new doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // Example method to add a new medication
    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    // Example method to add a new prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    // Methods to delete a patient, doctor, or medication
    public void deletePatient(String id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }

    public void deleteDoctor(String id) {
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }

    public void deleteMedication(String id) {
        medications.removeIf(medication -> medication.getId().equals(id));
    }

    public boolean deleteMedicationById(String id) {
        return medications.removeIf(medication -> medication.getId().equals(id));
    }

    public boolean deleteMedicationByName(String name) {
        return medications.removeIf(medication -> medication.getName().equalsIgnoreCase(name));
    }

    // Methods to edit a patient, doctor, or medication
    public void editPatient(String id, String name, int age, String phoneNumber) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                patient.setName(name);
                patient.setAge(age);
                patient.setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    public void editDoctor(String id, String name, int age, String phoneNumber, String specialization) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                doctor.setName(name);
                doctor.setAge(age);
                doctor.setPhoneNumber(phoneNumber);
                doctor.setSpecialization(specialization);
                break;
            }
        }
    }

    public void editMedication(String id, String name, String dose, int quantityInStock, Date expiryDate) {
        for (Medication medication : medications) {
            if (medication.getId().equals(id)) {
                medication.setName(name);
                medication.setDose(dose);
                medication.setQuantityInStock(quantityInStock);
                medication.setExpiryDate(expiryDate);
                break;
            }
        }
    }

    // Methods to search for a patient, doctor, or medication by name
    public Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }

    public Medication findMedicationByName(String name) {
        for (Medication medication : medications) {
            if (medication.getName().equalsIgnoreCase(name)) {
                return medication;
            }
        }
        return null;
    }

    // Methods to find doctor, patient, and medication by ID
    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public Medication findMedicationById(String id) {
        for (Medication medication : medications) {
            if (medication.getId().equals(id)) {
                return medication;
            }
        }
        return null;
    }

    // Method to assign a patient to a doctor's list
    public void addPatientToDoctor(String doctorId, Patient patient) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor != null) {
            doctor.getPatients().add(patient);
        }
    }

    // Getters for the lists
    public List<Patient> getPatients() {
        return patients;
    }   

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
}