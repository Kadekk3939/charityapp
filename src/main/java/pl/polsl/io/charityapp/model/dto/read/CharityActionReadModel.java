package pl.polsl.io.charityapp.model.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityActionReadModel {
    private String name;
    private String description;
    private Float goal;
    private Date endDate;
    private boolean shortDescription;
    private String directory;
    private List<String> images = new ArrayList<>();
    private Float raised;
    private List<DonationReadModel> topDonors;

    public String getDescription() {
        int len = 30;
        String ending = description.length() < len ? "" :"...";
        return shortDescription ?
                description.substring(0, Math.min(description.length(), len)) + ending
                : description;
    }
}
