package MedicationTracking;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

