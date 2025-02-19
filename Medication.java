package MedicationTracking;

import java.util.Date;

public class Medication {
    private String id;
    private String name;
    private String dose;
    private int quantityInStock;
    private Date expiryDate;

    public Medication(String id, String name, String dose, int quantityInStock, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    