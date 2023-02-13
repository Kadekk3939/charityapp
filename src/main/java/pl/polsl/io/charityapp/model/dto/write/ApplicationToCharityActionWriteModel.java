package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationToCharityActionWriteModel {
    private String charityActionName;
    private String reason;
    private List<String> documents;
}


