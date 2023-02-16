package pl.polsl.io.charityapp.model.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationToCharityActionReadModel {
    private UserReadModel benefactor;
    private CharityActionReadModel charityAction;
    private String reason;
    private ApplicationStatus status;
    private List<DocumentReadModel> documents;
}


