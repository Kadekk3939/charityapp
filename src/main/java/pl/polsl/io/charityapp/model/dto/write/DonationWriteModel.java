package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;

@Data
public class DonationWriteModel {
    private String charityActionName;
    private Float amount;
    private Boolean anonymous;
}
