package Model;

import java.time.LocalDateTime;

public record Appointment(int patientId,int doctorId, LocalDateTime dateTime, Specializaion specializaion) {
}

//        When a patient books an appointment the system creates it with an ID, a date, and a time.