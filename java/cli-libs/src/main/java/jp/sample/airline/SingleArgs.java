package jp.sample.airline;

import io.airlift.airline.Command;
import io.airlift.airline.HelpOption;
import io.airlift.airline.Option;

import javax.inject.Inject;

@Command(name = "foo", description = "network test utility")
public class SingleArgs {
	@Inject
	public HelpOption helpOption;

	@Option(name = { "-log", "-verbose" }, description = "Send count packets")
	public int verbose = 1;

	@Option(name = { "-groups" }, description = "Send count packets")
	public String groups;

	@Option(name = { "-debug" }, description = "Send count packets")
	public boolean debug = false;
}
