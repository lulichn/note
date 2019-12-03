package jp.sample.google;

import com.google.devtools.common.options.OptionsParser;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class GoogleOptionsTest {
	@Test
	public void test() {
		String[] argv = { "--host", "foo.com", "-p", "12345", "--dir", "foo", "-d", "bar", "-d", "baz" };

		OptionsParser parser = OptionsParser.newOptionsParser(Args.class);
		parser.parseAndExitUponError(argv);

		Args args = parser.getOptions(Args.class);

		assertThat(args.host, is("foo.com"));
		assertThat(args.port, is(12345));
		assertThat(args.dirs, contains("foo", "bar", "baz"));
	}
}
