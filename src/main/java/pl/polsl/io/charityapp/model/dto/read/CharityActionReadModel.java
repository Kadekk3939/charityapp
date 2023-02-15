package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class CharityActionReadModel {
    private String name;
    private String description;
    private Float goal;
    private Date endDate;
    private boolean shortDescription;

    //TODO: money collected
    private String directory;
    private List<String> images = new ArrayList<>();

    public String getDescription() {
        int len = 30;
        return shortDescription ?
                description.substring(0, Math.min(description.length(), len)) + (description.length() < len ? "" :"...")
                : description;
    }
}
