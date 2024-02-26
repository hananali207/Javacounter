package accidentpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Program4 {
	private static final String CSV_FILE_PATH = "/Users/hananali/eclipse-workspace/Project1DS/src/accidentpack/accidents.csv";
	private static Report[] reports;
	private static ArrayList<Report> reportList;

	public Program4() throws IOException {
		reports = Report.readReportsFromFile(CSV_FILE_PATH);
		reportList = new ArrayList<Report>(Arrays.asList(reports));
		Collections.sort(reportList, Comparator.comparing(Report::getDaysSinceCE));
	}

	public static void main(String[] args) throws IOException {
		Program4 program = new Program4();

		program.simulateProcess("CA", "Los Angeles");
		program.simulateProcess("FL", "Orange");
		program.simulateProcess("TX", "Harris");
		program.simulateProcess("OH", "Hamilton");
		program.simulateProcess("DE", "New Castle");

	
	}

	/**
	 * Constructs an AccidentQueue containing reports matching the specified state
	 * and county.
	 *
	 * @param state  the state abbreviation
	 * @param county the county name
	 * @return an AccidentQueue containing matching reports
	 */
	public static AccidentQueue constructQueue(String state, String county) {
		AccidentQueue newQueue = new AccidentQueue(10000);
		// Add reports matching criteria to the queue
		for (Report report : reportList) {
			if (report.getState().equalsIgnoreCase(state) && report.getCounty().equalsIgnoreCase(county)) {
				newQueue.offer(report);

			}
		}
		return newQueue;
	}

	/**
	 * Calculates the minimum number of counters needed for processing reports in
	 * the specified queue.
	 *
	 * @param state   the state abbreviation
	 * @param county  the county name
	 * @param reports the queue of reports
	 * @return the minimum number of counters needed
	 */
	public int getMinCountersNeeded(String state, String county, AccidentQueue reports) {
		ArrayList countersNeededForEachDay = new ArrayList<Report>();
		int firstDay = reports.peek().getDaysSinceCE();
		int lastDay = reports.peekLast().getDaysSinceCE();
		// Debugging: Check the number of reports loaded
		// System.out.println("Total reports loaded: " + reports.size());

		// Iterate through each report
		for (int i = firstDay; i < lastDay + 1; i++) {
			int countersNeeded = 0;
			int runningSum = 0;
			while (!reports.isEmpty() && reports.peek().getDaysSinceCE() == i) {
				Report currentReport = reports.poll();
				runningSum += currentReport.getSeverity() * 60;
			}
			// Adding severity * 60 to running sum
			// If running sum exceeds one day (1440 minutes), add counters needed for the
			// day
			if (runningSum >= 1440) {
				countersNeeded += (runningSum / 1440);
				runningSum %= 1440;
			}
			// If there are remaining minutes after processing all reports, add one more
			// counter
			if (runningSum > 0) {
				countersNeeded += 1;
			}
			countersNeededForEachDay.add(countersNeeded);
		}

		// Debugging: Print the counters needed array
		// System.out.println("Counters needed: " +
		// Collections.max(countersNeededForEachDay));

		// Return the maximum counters needed for any day
		return (int) Collections.max(countersNeededForEachDay);

	}

	public void simulateProcess(String state, String county) {
		AccidentQueue queue = constructQueue(state, county);
		int minCountersNeeded = getMinCountersNeeded(state, county, queue);

		System.out.println("County: " + county + " State: " + state);
		System.out.println("Minimum number of counters: " + minCountersNeeded);
		System.out.println();

		long start = System.currentTimeMillis();
		while (!queue.isEmpty()) {
			getMinCountersNeeded(state, county, queue);
		}
		long end = System.currentTimeMillis();
		long timeTaken = end-start;
		System.out.println(timeTaken + " seconds to simulate the process");
	}
}