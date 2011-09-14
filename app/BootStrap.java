import java.util.ArrayList;
import java.util.List;
import play.*;
import play.jobs.*;
import play.test.*;
import models.*;

import org.apache.log4j.*;
import org.apache.log4j.Logger;

@OnApplicationStart
public class BootStrap extends Job {

	private static Logger logger = LogManager.getLogger(BootStrap.class);

	public void doJob() {

		// Check if the database is empty
		if (Student.count() == 0) {

			// create a student here;
			User user = new User("Sam", "secret", "sam@gmail.com");
			user.isAdmin = false;
			user.isEmployee = false;
			user.isStudent = true;

			// personal details
			PersonalDetails personalDetails = new PersonalDetails();
			personalDetails.firstName = "Sam";
			personalDetails.lastName = "Khawse";
			personalDetails.addressLine1 = "5032 EP True";
			personalDetails.city = "West Des Moines";

			// enrollment Details
			EnrolmentDetails ed = new EnrolmentDetails();
			ed.enrolmentNumber = 1234;
			ed.rollNumber = 34;

			// Scores
			long[] scoresEarned = { 12, 13, 14, 15 };
			Scores scores = new Scores("Maths", "Pande", scoresEarned);
			List<Scores> scoresList = new ArrayList();
			scoresList.add(scores);

			// Course
			List<String> subjects = new ArrayList();
			subjects.add("Maths");
			subjects.add("Physics");
			subjects.add("Chemistry");

			Course course = new Course("3rd Year", "Pande", subjects);
			course.save();

			// now create a student
			Student student = new Student();
			student.currentCourse = course;
			student.user = user;
			student.personalDetails = personalDetails;
			student.enrolmentDetails = ed;
			student.scores = scoresList;

			logger.info("Student created");
			student.save();

			// end check
		}else{
			logger.info("Students found: "+Student.count());
		}
	}

}