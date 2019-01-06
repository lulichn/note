package jp.sample.picocli;

import picocli.CommandLine;

import java.io.File;

public class Args {
	@CommandLine.Option(names = { "-v", "--verbose" }, description = "Be verbose.")
	boolean verbose = false;

	@CommandLine.Option(names = { "-h", "--help" }, usageHelp = true,
			description = "Displays this help message and quits.")
	boolean helpRequested = false;

	@CommandLine.Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to process.")
	File[] inputFiles;
}
