package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;

import java.sql.Date;

@Data
public class CharityActionReadModel {
    private String name;
    private String description;
    private Float goal;
    private Date endDate;
    private boolean shortDescription = false;

    public String getDescription() {
        return shortDescription ? description.substring(0, Math.min(description.length(), 30)) + "..." : description;
    }
}
