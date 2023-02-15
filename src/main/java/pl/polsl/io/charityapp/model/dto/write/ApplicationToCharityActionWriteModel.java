package pl.polsl.io.charityapp.model.dto.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationToCharityActionWriteModel {
    private String charityActionName;
    private String reason;
}


