package my

import java.nio.file.Path

public class GlobalLogger {

	private Path outFile_
	private OutputStream os_

	protected GlobalLogger(Path outFile) {
		Objects.requireNonNull(outFile)
		outFile_ = outFile
		os_ = new FileOutputStream(outFile_.toFile())
	}

	void log(String message) {
		os_.write(message.getBytes())
	}
}
