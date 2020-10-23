package team.baymax.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import team.baymax.commons.core.time.DateTime;
import team.baymax.model.appointment.Appointment;
import team.baymax.model.appointment.AppointmentStatus;
import team.baymax.model.appointment.Description;
import team.baymax.model.listmanagers.AppointmentManager;
import team.baymax.model.listmanagers.PatientManager;
import team.baymax.model.listmanagers.ReadOnlyListManager;
import team.baymax.model.patient.Gender;
import team.baymax.model.patient.Name;
import team.baymax.model.patient.Nric;
import team.baymax.model.patient.Patient;
import team.baymax.model.patient.Phone;
import team.baymax.model.patient.Remark;
import team.baymax.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AppointmentManager} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Nric("S0123456A"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new Gender("M"), getTagSet(), new Remark("Allergic to ibuprofen.")),
            new Patient(new Nric("T0123456A"), new Name("Bernice Yu"), new Phone("99272758"),
                    new Gender("F"), getTagSet(),
                    new Remark("Only available on Saturdays.")),
            new Patient(new Nric("S6543210A"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Gender("F"), getTagSet("CHAS", "LTC"), new Remark("Under long term care.")),
            new Patient(new Nric("T6543210A"), new Name("David Li"), new Phone("91031282"),
                    new Gender("M"), getTagSet("Paediatric"), new Remark("Diabetic.")),
            new Patient(new Nric("T1548765D"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Gender("M"), getTagSet("CHAS"), new Remark("Allergic to aspirin.")),
            new Patient(new Nric("S4658753E"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Gender("M"), getTagSet("LTC"), new Remark("Currently taking warfarin."))
        };
    }

    public static ReadOnlyListManager<Patient> getSamplePatientManager() {
        PatientManager samplePM = new PatientManager();
        for (Patient samplePatient : getSamplePatients()) {
            samplePM.addPatient(samplePatient);
        }
        return samplePM;
    }

    public static Appointment[] getSampleAppointments() {
        Patient[] patients = getSamplePatients();
        return new Appointment[] {
            new Appointment(patients[0], DateTime.fromString("10-10-2020 14:00"),
                    AppointmentStatus.DONE, new Description("Full body checkup"), getTagSet("Xray")),
            new Appointment(patients[3], DateTime.fromString("28-10-2020 10:45"),
                    AppointmentStatus.UPCOMING, new Description("Diabetes checkup"), getTagSet()),
            new Appointment(patients[0], DateTime.fromString("10-01-2021 12:00"),
                    AppointmentStatus.UPCOMING, new Description("Blood pressure checkup"), getTagSet()),
            new Appointment(patients[2], DateTime.fromString("15-10-2020 09:30"),
                    AppointmentStatus.MISSED, new Description("Wrist fracture checkup"), getTagSet("Xray")),
            new Appointment(patients[1], DateTime.fromString("23-11-2020 15:00"),
                    AppointmentStatus.UPCOMING, new Description("Pregnancy checkup"), getTagSet("Gynaecology")),
            new Appointment(patients[1], DateTime.fromString("02-01-2021 15:00"),
                    AppointmentStatus.UPCOMING, new Description("Pregnancy checkup"), getTagSet("Gynaecology"))
        };
    }

    public static ReadOnlyListManager<Appointment> getSampleAppointmentManager() {
        AppointmentManager sampleAM = new AppointmentManager();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAM.addAppointment(sampleAppointment);
        }
        return sampleAM;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}