package project.server;

public enum Status {

    READY(1, "Ready")
    , GAMING(2, "Gaming")
    , FINISHED(3, "Finished");

    private int statusCode;
    private String statusName;

    Status(int statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }
}
