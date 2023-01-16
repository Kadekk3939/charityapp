package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;

@Data
public class DonorRatingWriteModel {
    private String donorLogin;
    private String charityActionName;
    private boolean rating;
}
