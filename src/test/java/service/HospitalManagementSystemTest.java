package service;

import Model.Appointment;
import Model.Doctor;
import Model.Specializaion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalManagementSystemTest {

    private List<Appointment> listofAppointments;
    private Appointment appointmentOne;
    private Appointment appointmentTwo;
    private Appointment appointmentThree;
    private Appointment appointmentFour;
    private Appointment appointmentFive;
    private Appointment appointmentSix;
    private HospitalManagementSystem hospitalManagementSystem;

    private  Doctor doctorOne;
    private  Doctor doctorTwo;
    private  Doctor doctorThree;

    @BeforeEach
    void setUp() {
        LocalDateTime localDateTimeOne = LocalDateTime.of(2023, 9, 28, 12, 0, 0);
        LocalDateTime localDateTimeTwo = LocalDateTime.of(2023, 9, 20, 10, 30, 0); // Future appointment
        LocalDateTime localDateTimeThree = LocalDateTime.of(2022, 8, 15, 14, 15, 0); // Past appointment
        LocalDateTime localDateTimeFour = LocalDateTime.of(2023, 8, 25, 14, 45, 0); // Future appointment
        LocalDateTime localDateTimeFive = LocalDateTime.of(2023, 8, 23, 9, 0, 0);   // Future appointment
        LocalDateTime localDateTimeSix = LocalDateTime.of(2023, 8, 19, 16, 30, 0);  // Past appointment


        doctorOne = new Doctor(1, "Heinrich", Specializaion.SURGERY, 20);
        doctorTwo = new Doctor(1, "Heinrich", Specializaion.SURGERY, 30);
        doctorThree = new Doctor(1, "Heinrich", Specializaion.SURGERY, 25);
        appointmentOne = new Appointment(1, 3, localDateTimeOne, Specializaion.SURGERY);
        appointmentTwo = new Appointment(2, 4, localDateTimeTwo, Specializaion.PATHOLOGY);
        appointmentThree = new Appointment(3, 3, localDateTimeThree, Specializaion.INTERNAL);
        appointmentFour = new Appointment(6, 2, localDateTimeFour, Specializaion.PATHOLOGY);
        appointmentFive = new Appointment(6, 1, localDateTimeFive, Specializaion.SURGERY);
        appointmentSix = new Appointment(6, 5, localDateTimeSix, Specializaion.INTERNAL);

        listofAppointments = List.of(appointmentOne, appointmentTwo, appointmentThree, appointmentFour, appointmentFive, appointmentSix);
        hospitalManagementSystem = new HospitalManagementSystem();
    }


    @Test
    void allPatientsWhoHaveAppointmentInFutureWithCertainDoctor() {
//        r(LocalDateTime dateTime, List< Appointment > appointment, Doctor doctor)
//        t(int id, LocalDateTime dateTime) {
//        }
        LocalDateTime localDateTimeOne = LocalDateTime.of(2023, 9, 19, 12, 0, 0);
        Doctor doctor = new Doctor(1,"Heinrich", Specializaion.SURGERY,20);
        List<Appointment> listOfAppointments = hospitalManagementSystem.allPatientsWhoHaveAppointmentInFutureWithCertainDoctor(localDateTimeOne, listofAppointments,doctor);
        List<Appointment> expexcted = List.of(appointmentOne);
        assertEquals(expexcted,listOfAppointments );
    }

    @Test
    void patienWithMostAppointmentsLastThirtyDays() {
        // - Get the patient who had the most appointments in the last 30 days.
        LocalDate localDate = LocalDate.now();
        Optional<Integer> patientId = hospitalManagementSystem.patienWithMostAppointmentsLast30(listofAppointments, localDate);
        System.out.println("patientId = " + patientId);
        assertEquals(Optional.of(6),patientId);
    }

    @Test
    void doctorWithHighestExpierience() {
        List<Doctor> doctors = List.of(doctorOne,doctorTwo,doctorThree);
        Optional<Doctor> doctor = hospitalManagementSystem.doctorWithHighestExpierience(doctors);
        assertEquals(Optional.of(doctorTwo),doctor);
    }
}


// - Create a function that retrieves all patients who have appointments (in the future) with a certain doctor (by doctor specialization).
// - Get the patient who had the most appointments in the last 30 days.
// - Get the doctor with the highest experience..