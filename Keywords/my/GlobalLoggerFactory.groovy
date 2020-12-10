package my

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.configuration.RunConfiguration

/**
 * GlobalLoggerFactory creates a Singleton instance of GlobalLogger
 * 
 * @author kazurayam
 */
public class GlobalLoggerFactory {

	private static GlobalLogger instance_
	private static String logFileName_

	static {
		instance_ = null
		logFileName_ = 'my.log'
	}

	private GlobalLoggerFactory() {}

	static GlobalLogger getInstance(String dirName) throws IOException {
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path dir = projectDir.resolve(dirName)
		return getInstance(dir)
	}

	static GlobalLogger getInstance(Path dir) throws IOException {
		Objects.requireNonNull(dir)
		// Singleton pattern
		if (this.instance_ == null) {
			Path outFile = dir.resolve(getTimestampString()).resolve(logFileName_)
			Files.createDirectories(outFile.getParent())
			this.instance_ = new GlobalLogger(outFile)
		}
		return this.instance_
	}

	static terminate() {
		this.instance_ = null
	}

	static String getTimestampString() {
		Instant instant = Instant.now()
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		return dateTimeFormatter.format(ldt)
	}
}
