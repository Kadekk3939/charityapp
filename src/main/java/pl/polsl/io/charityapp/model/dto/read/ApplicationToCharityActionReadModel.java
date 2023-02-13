package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import java.util.List;

@Data
public class ApplicationToCharityActionReadModel {
    private UserReadModel benefactor;
    private CharityActionReadModel charityAction;
    private String reason;
    private ApplicationStatus status;
    private List<DocumentReadModel> documents;
}


