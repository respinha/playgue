package common;

import entities.Reporter;

/**
 * Created by espinha on 1/24/17.
 */
public class Report {

    private Reporter reporter;
    private String message;

    public Reporter getReporter() {
        return reporter;
    }

    public String getMessage() {
        return message;
    }

    public Report(Reporter reporter, String message) {
        this.reporter = reporter;
    }

}
