package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;

import java.sql.Date;

@Data
public class CharityActionReadModel {

    private String name;

    private String description;

    private Float goal;

    private Date endTime;
}
