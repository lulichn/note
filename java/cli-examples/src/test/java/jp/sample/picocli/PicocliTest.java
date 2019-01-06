package jp.sample.picocli;

import org.junit.Test;
import picocli.CommandLine;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;

public class PicocliTest {
	@Test
	public void test() {
		String[] argv = { "-v", "inputFile1", "inputFile2" };
		Args args = CommandLine.populateCommand(new Args(), argv);

		assertThat(args.verbose, is(true));
		assertThat(args.inputFiles, is(arrayContaining(new File("inputFile1"), new File("inputFile2"))));
	}

	@Test
	public void help() {
		String[] argv = { "--help", "-v", "inputFile1", "inputFile2" };
		Args args = CommandLine.populateCommand(new Args(), argv);

		assertThat(args.helpRequested, is(true));
		assertThat(args.verbose, is(true));

		CommandLine.usage(new Args(), System.out);
	}

	@Test
	public void invalid() {
		String[] argv = { "--dummy" };
		Args args = CommandLine.populateCommand(new Args(), argv);
	}
}
