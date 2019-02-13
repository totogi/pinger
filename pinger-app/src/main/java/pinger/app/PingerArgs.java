package pinger.app;

import static picocli.CommandLine.*;

public class PingerArgs {

    @Option(names = { "--url" }, required = true, description = "Url to check")
    public String url;

    @Option(names = { "--phone" }, required = true, description = "Phone number to alert")
    public String phone;

    @Option(names = { "--interval" }, description = "Number of minutes to wait between checks")
    public Long interval;

    @Option(names = { "--threshold" }, description = "Maximum number of errors to encounter before alerting")
    public Integer threshold;
}
