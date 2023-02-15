package pl.polsl.io.charityapp.model.dto.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharityActionWriteModel {
    private String name;
    private String description;
    private Float goal;
    private Date endDate;
}
