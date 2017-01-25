package patientenportal.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CaseFileTest.class, DoctorTest.class, MedicationPrescriptionTest.class, PatientFileTest.class,
		PatientTest.class, TreatmentTest.class, UserTest.class })
public class EnpoindTests {

}
