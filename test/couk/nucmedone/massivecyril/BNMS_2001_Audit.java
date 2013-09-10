package couk.nucmedone.massivecyril;

import java.text.ParseException;
import java.util.Calendar;

import com.ibm.icu.text.SimpleDateFormat;

import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientName;
import couk.nucmedone.massivecyril.shared.labtest.AbstractCountingTube;
import couk.nucmedone.massivecyril.shared.labtest.CorrectedCounts;
import couk.nucmedone.massivecyril.shared.labtest.Counts;
import couk.nucmedone.massivecyril.shared.labtest.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.Injection;
import couk.nucmedone.massivecyril.shared.labtest.SampleTube;
import couk.nucmedone.massivecyril.shared.labtest.Standard;
import couk.nucmedone.massivecyril.shared.labtest.StandardsTube;

public class BNMS_2001_Audit {

	private static final int AUDIT_NUMBER = 10;
	private static final SimpleDateFormat countDate = new SimpleDateFormat(
			"DD-MMM-YYYY HH:mm:ss");

	private static Injection[] injections;
	private static SampleTube[] samples;
	private static Standard[] standards;
	private static Patient[] patients;

	public static Injection[] getInjections() {
		if (injections == null) {
			// When?
			Calendar cal = Calendar.getInstance();
			cal.set(2001, Calendar.FEBRUARY, 01, 9, 0);
			injections = new Injection[] { new Injection(10010d, 10d, 10d,
					4.535, 6.573, cal) };
		}
		return injections;
	}

	public static Standard[] getStandards() throws ParseException {
		if (standards == null) {

			// The count date
			Calendar date = Calendar.getInstance();
			date.setTime(countDate.parse("06-Feb-2001 18:46:46"));
			date.set(Calendar.YEAR, 2000);

			standards = new Standard[AUDIT_NUMBER];
			standards[0] = new Standard("");

			// Count date
			standards[0].setCountDate(date);
			// Just make reference date the same as the count date
			standards[0].setRefDate(date);
			// Count volume
			standards[0].setCountVolume(new DoublePlus(5, AbstractCountingTube.VOLUME_ERROR));
			// Counts
			standards[0].setCounts(new CorrectedCounts(35281, 0, 1200));
			// Weights
			standards[0].setEmptyWeight(new DoublePlus(4, AbstractCountingTube.WEIGHT_ERROR));
			standards[0].setFullWeight(new DoublePlus(4.5, AbstractCountingTube.WEIGHT_ERROR));
		}

		return standards;
	}

	// "Patient1","AUDIT001",70,1.78,"06-Feb-2000",""
	// 1,"001","06-Feb-2001","00:00","C:\term\audit24\001.std","001.std"
	// "06-Feb-2001","06-Feb-2001","C:\TERM\AUDIT24\AUDT_R1.T","AUDT_R1.T",36,"","0",0,0,3
	// "30-Dec-1899","30-Dec-1899","C:\TERM\AUDIT24\AUDT_S1.T","AUDT_S1.T",31,"","0",0,0,0
	// 4.535,6.573,"09:00",10,10010,10,2.038
	// 3,"11:00",1,3
	// 4,"12:00",1,3
	// 5,"13:00",1,3
	// 153.108766559351,7.06660015728456,141.56590466927,6.53384954162111
	// 126.644721795837,.724658984640941,24479.972832351,1121.13506760473
	// 3.10997975218811E-05,1.42431014266118E-06,1.33054697503445E-08,1.4240519917992E-06

	public static SampleTube[] getSamples() {

		if (samples == null) {
			SampleTube sample1 = new SampleTube("One");

			// Sample time
			Calendar cal = Calendar.getInstance();
			cal.set(2001, Calendar.FEBRUARY, 01, 11, 0);
			sample1.setSampleTime(cal);
			// Count time
			cal.set(Calendar.HOUR_OF_DAY, 10);
			cal.set(Calendar.MINUTE, 41);
			sample1.setCountDate(cal);
			// Empty weight
			sample1.setEmptyWeight(new DoublePlus(1d, AbstractCountingTube.WEIGHT_ERROR));
			// Full weight
			sample1.setFullWeight(new DoublePlus(3d, AbstractCountingTube.WEIGHT_ERROR));
			// Set sample counts & background
			sample1.setCounts(new CorrectedCounts(new Counts(4600, 3600),
					new Counts(10, 3600)));

			samples = new SampleTube[] { sample1 };
		}

		return samples;
	}

	public static Patient[] getPatients() {
		if (patients == null) {
			Patient patient1 = new Patient();
			// Name
			patient1.setPatientName(new PatientName("AUDIT2001", "ONE", "", "",
					""));
			// Date of birth
			Calendar dob = Calendar.getInstance();
			dob.set(Calendar.YEAR, 1970);
			patient1.setDateOfBirth(dob);
			// height and weight
			patient1.setHeight(1.78);
			patient1.setWeight(70);
			// ID
			patient1.setPrimaryID("audit2001-1");

			patients = new Patient[] { patient1 };
		}
		return patients;
	}
}
