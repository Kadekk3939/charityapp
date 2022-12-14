package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import javax.persistence.*;

@Data
public class ApplicationToCharityActionReadModel {

    private User benefactor;

    private CharityActionReadModel charityAction;

    private String reason;

//    private List<Document> documents;
}


