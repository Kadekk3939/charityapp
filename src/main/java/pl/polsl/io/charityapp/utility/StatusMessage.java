package pl.polsl.io.charityapp.utility;

public class StatusMessage {
    private final boolean status;
    private final String errorMessage;

    public StatusMessage() {
        this.status = true;
        this.errorMessage = null;
    }

    public StatusMessage(String errorMessage) {
        this.status = false;
        this.errorMessage = errorMessage;
    }
}
