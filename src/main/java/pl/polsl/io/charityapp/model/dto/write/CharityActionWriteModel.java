package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;

import java.sql.Date;

@Data
public class CharityActionWriteModel {

    private String name;

    private String description;

    private Float goal;

    private Date endDate;
}
