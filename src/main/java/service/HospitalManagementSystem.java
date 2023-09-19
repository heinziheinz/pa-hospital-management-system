package service;

import Model.Appointment;
import Model.Doctor;
import Model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HospitalManagementSystem {

    public List<Appointment> allPatientsWhoHaveAppointmentInFutureWithCertainDoctor(LocalDateTime dateTime, List<Appointment> appointment, Doctor doctor){
        return appointment.stream().filter(appointment1 -> appointment1.dateTime().isAfter(dateTime))
                .filter(appointment1 -> appointment1.specializaion() == doctor.specializaion()).toList();
    }

    public Optional<Integer> patienWithMostAppointmentsLast30(List<Appointment> appointmentList, LocalDate today){
        // Calculate the date 30 days ago

        LocalDate thirtyDaysAgo = today.minusDays(30);
        Map<Integer, Long> appointments = appointmentList.stream().
                filter(appointment -> appointment.dateTime().toLocalDate().isAfter(thirtyDaysAgo)
                        && appointment.dateTime().toLocalDate().isBefore(today))
                .collect(Collectors.groupingBy(
                        Appointment::patientId,
                        Collectors.counting()
                ));
        return appointments.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);

    }


    public Optional<Doctor> doctorWithHighestExpierience(List<Doctor> doctors){

        return doctors.stream().max(Comparator.comparing(Doctor::yearsOfExperience));
    }
}

// - Create a function that retrieves all patients who have appointments (in the future) with a certain doctor (by doctor specialization).
// - Get the patient who had the most appointments in the last 30 days.
// - Get the doctor with the highest experience..

