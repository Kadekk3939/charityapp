package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import javax.persistence.*;

@Data
public class ApplicationToCharityActionWriteModel {

    private String charityActionName;
    private String reason;

//    private List<Document> documents;
}


