package project.server;

public enum Status {

    Ready(1, "Ready")
    , Gaming(2, "Gaming")
    , Finished(3, "Finished");

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
