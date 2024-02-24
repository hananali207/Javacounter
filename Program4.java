package accidentpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Program4 {
	private static final String CSV_FILE_PATH = "/Users/jason/Downloads/accidents.csv";
	private static final int ARRAY_SIZE = 2500000;
	private static Report[] reports;
	private static ArrayList<Report> reportList;
	public Program4() throws IOException {
		reports = Report.readReportsFromFile(CSV_FILE_PATH);
		reportList = new ArrayList<Report>(Arrays.asList(reports));
		Collections.sort(reportList, Comparator.comparing(Report::getDaysSinceCE));
	}

	public static void main(String[] args) throws IOException {
		Program4 program = new Program4();
		System.out.println("Minimum number of counters needed:");
		System.out.println("Los Angeles, CA: " + program.getMinCountersNeeded("CA", "Los Angeles", constructQueue("CA", "Los Angeles")));
		System.out.println("Orange, FL: " + program.getMinCountersNeeded("FL", "Orange", constructQueue("FL", "Orange")));
		System.out.println("Harris, TX: " + program.getMinCountersNeeded("TX", "Harris", constructQueue("TX", "Harris")));
		System.out.println("Hamilton, OH: " + program.getMinCountersNeeded("OH", "Hamilton", constructQueue("OH", "Hamilton")));
		System.out.println("New Castle, DE: " + program.getMinCountersNeeded("DE", "New Castle", constructQueue("DE", "New Castle")));
	}

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

	public int getMinCountersNeeded(String state, String county, AccidentQueue reports) {
		ArrayList countersNeededForEachDay = new ArrayList<Report>();
		int firstDay = reports.peek().getDaysSinceCE();
		int lastDay = reports.peekLast().getDaysSinceCE();
		// Debugging: Check the number of reports loaded
		System.out.println("Total reports loaded: " + reports.size());

		// Iterate through each report
		for (int i = firstDay; i < lastDay + 1; i++) {
			int countersNeeded = 0;
			int runningSum = 0;
			while(!reports.isEmpty() && reports.peek().getDaysSinceCE() == i){
				Report currentReport = reports.poll();
				runningSum += currentReport.getSeverity() * 60;
			}
			// Adding severity * 60 to running sum
			// If running sum exceeds one day (1440 minutes), add counters needed for the
			// day
			if (runningSum >= 1440) {
				countersNeeded+=(runningSum / 1440);
				runningSum %= 1440;
			}
			// If there are remaining minutes after processing all reports, add one more
			// counter
			if (runningSum > 0) {
				countersNeeded+= 1;
			}
			countersNeededForEachDay.add(countersNeeded);
		}
		

		// Debugging: Print the counters needed array
		System.out.println("Counters needed: " + Collections.max(countersNeededForEachDay));

		// Check if countersNeeded is empty before finding the minimum
		return (int) Collections.max(countersNeededForEachDay);

	}
}