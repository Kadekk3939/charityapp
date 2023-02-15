package pl.polsl.io.charityapp.model.dto.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorRatingWriteModel {
    private String donorLogin;
    private String charityActionName;
    private boolean rating;
}
