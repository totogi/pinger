package pinger.app;

import static picocli.CommandLine.*;

public class PingerArgs {

    @Option(names = { "--account" }, required = true, description = "Twilio Account Id")
    public String account;

    @Option(names = { "--authToken" }, required = true, description = "Twilio Auth Token")
    public String authToken;

    @Option(names = { "--url" }, required = true, description = "Url to ping")
    public String url;

    @Option(names = { "--phone" }, required = true, description = "Phone number to alert")
    public String phone;

    @Option(names = { "--interval" }, description = "Number of minutes to wait between checks")
    public Long interval;

    @Option(names = { "--threshold" }, description = "Maximum number of errors to encounter before alerting")
    public Integer threshold;
}
