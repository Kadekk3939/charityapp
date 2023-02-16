package pl.polsl.io.charityapp.model.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationReadModel {
    private String fullName;

    private String charityActionName;

    private Float amount;

    private Boolean paymentConfirmed;

    private Boolean anonymous;
}
