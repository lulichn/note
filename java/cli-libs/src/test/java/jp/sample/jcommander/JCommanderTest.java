package jp.sample.jcommander;

import com.beust.jcommander.JCommander;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JCommanderTest {
	@Test
	public void test() {
		Args args = new Args();

		String[] argv = { "-log", "2", "-groups", "unit" , "-debug", "true"};

		JCommander.newBuilder()
				.addObject(args)
				.build()
				.parse(argv);

		assertThat(args.verbose, is(2));
		assertThat(args.groups, is("unit"));
		assertThat(args.debug, is(true));
	}
}
