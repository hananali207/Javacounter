package accidentpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Program4 {
	private static final String CSV_FILE_PATH = "/Users/hananali/eclipse-workspace/Project1DS/src/accidentpack/accidents.csv";
	private static final int ARRAY_SIZE = 2500000;
	private static Report[] reports;

	public Program4() throws IOException {
		reports = Report.readReportsFromFile(CSV_FILE_PATH);
	}

	public static void main(String[] args) throws IOException {
		Program4 program = new Program4();

		System.out.println("Minimum number of counters needed:");
		System.out.println("Los Angeles, CA: " + program.getMinCountersNeeded("CA", "Los Angeles"));
		System.out.println("Orange, FL: " + program.getMinCountersNeeded("FL", "Orange"));
		System.out.println("Harris, TX: " + program.getMinCountersNeeded("TX", "Harris"));
		System.out.println("Hamilton, OH: " + program.getMinCountersNeeded("OH", "Hamilton"));
		System.out.println("New Castle, DE: " + program.getMinCountersNeeded("DE", "New Castle"));
	}

	public ArrayList<String> getTimesOfDay(String state, String county, int year, int month, int day) {
		ArrayList<String> timesOfDay = new ArrayList<>();
		Queue<Report> reportQueue = new LinkedList<>();

		// Add reports matching criteria to the queue
		for (Report report : reports) {
			if (report.getState().equalsIgnoreCase(state) && report.getCounty().equalsIgnoreCase(county)
					&& report.getYear() == year && report.getMonth() == month && report.getDay() == day) {
				reportQueue.offer(report);
			}
		}

		// Sort the reports by start time
		reportQueue = sortReportsByTime(reportQueue);

		// Extract start and end times from the sorted reports
		while (!reportQueue.isEmpty()) {
			Report report = reportQueue.poll();
			timesOfDay.add(report.getStartTime());
			timesOfDay.add(report.getEndTime());
		}

		return timesOfDay;
	}

	private Queue<Report> sortReportsByTime(Queue<Report> reportQueue) {
		ArrayList<Report> temp = new ArrayList<>(reportQueue);
		Collections.sort(temp, Comparator.comparing(Report::getDateTime));
		reportQueue.clear();
		reportQueue.addAll(temp);
		return reportQueue;
	}

	public int getMinCountersNeeded(String state, String county) {
		int countersNeeded = 0;
		int runningSum = 0;

		// Debugging: Check the number of reports loaded
		System.out.println("Total reports loaded: " + reports.length);

		// Iterate through each report
		for (Report report : reports) {

			if (report.getState().equalsIgnoreCase(state) && report.getCity().equalsIgnoreCase(county)) {
				runningSum += report.getSeverity() * 60; // Adding severity * 60 to running sum

				// If running sum exceeds one day (1440 minutes), add counters needed for the
				// day

			}
		}
		if (runningSum >= 1440) {
			countersNeeded+=(runningSum / 1440);
			runningSum %= 1440;
		}
		// If there are remaining minutes after processing all reports, add one more
		// counter
		if (runningSum > 0) {
			countersNeeded+= 1;
		}

		// Debugging: Print the counters needed array
		System.out.println("Counters needed: " + countersNeeded);

		// Check if countersNeeded is empty before finding the minimum
		return countersNeeded;

	}
}