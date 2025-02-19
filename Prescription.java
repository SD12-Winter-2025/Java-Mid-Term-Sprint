package MedicationTracking;

import java.util.Date;

public class Prescription {
    private String id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private Date prescriptionExpiry;

    public Prescription(String id, Doctor doctor, Patient patient, Medication medication, Date prescriptionExpiry) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescriptionExpiry = prescriptionExpiry;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Date getPrescriptionExpiry() {
        return prescriptionExpiry;
    }

    public void setPrescriptionExpiry(Date prescriptionExpiry) {
        this.prescriptionExpiry = prescriptionExpiry;
    }
}