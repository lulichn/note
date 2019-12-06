package jp.sample.airline;

import io.airlift.airline.SingleCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineTest {
	@Test
	public void test() {
		String[] argv = { "-log", "2", "-groups", "unit" , "-debug"};

		SingleArgs args = SingleCommand.singleCommand(SingleArgs.class).parse(argv);

		assertThat(args.verbose, is(2));
		assertThat(args.groups, is("unit"));
		assertThat(args.debug, is(true));;

		if (args.helpOption.showHelpIfRequested()) {
			Assert.fail();
		}
	}

	@Test
	public void help() {
		String[] argv = { "--help"};

		SingleArgs args = SingleCommand.singleCommand(SingleArgs.class).parse(argv);

		if (args.helpOption.showHelpIfRequested()) {
			return;
		}
	}
}
