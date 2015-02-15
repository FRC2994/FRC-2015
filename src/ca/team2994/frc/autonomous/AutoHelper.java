package ca.team2994.frc.autonomous;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;

public class AutoHelper {
	
	/**
	 * A splitter to use when splitting strings by commas
	 */
	public static final Splitter SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

	/**
	 * A filter to skip comments
	 */
	public final static Predicate<String> SKIP_COMMENTS = new Predicate<String>() {
		public boolean apply(String str) {
			return !str.startsWith("//");
		}
	};	
	
	/**
	 * Writes a String to the file specified
	 * @param line The String to write to the file
	 * @param file The file to write to
	 * @return Whether the operation was successful or not
	 */
	@SuppressWarnings("resource")
	public static boolean writeLineToFile(String line, File file) {
		PrintStream stream = null;
		try {
			stream = new PrintStream(new FileOutputStream(file, false));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;	
		}

		
		stream.println(line);
		
		return true;
	}
}
