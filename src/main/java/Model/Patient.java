package Model;

import java.time.LocalDate;

public record Patient(int id, String name, LocalDate birthdate) {
}

//        Each patient should have a unique patient ID, a name, and a date of birth.
