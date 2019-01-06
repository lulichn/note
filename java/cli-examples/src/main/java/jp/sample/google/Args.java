package jp.sample.google;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

import java.util.List;

public class Args extends OptionsBase {
	@Option(
			name = "help",
			abbrev = 'h',
			help = "Prints usage info.",
			defaultValue = "false"
	)
	public boolean help;

	@Option(
			name = "host",
			abbrev = 'o',
			help = "The server host.",
			category = "startup",
			defaultValue = ""
	)
	public String host;

	@Option(
			name = "port",
			abbrev = 'p',
			help = "The server port.",
			category = "startup",
			defaultValue = "8080"
	)
	public int port;

	@Option(
			name = "dir",
			abbrev = 'd',
			help = "Name of directory to serve static files.",
			category = "startup",
			allowMultiple = true,
			defaultValue = ""
	)
	public List<String> dirs;
}
