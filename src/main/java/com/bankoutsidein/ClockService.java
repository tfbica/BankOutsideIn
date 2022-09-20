package com.bankoutsidein;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClockService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String getCurrentDate() {
        return LocalDate.now().format(formatter);
    }
}
