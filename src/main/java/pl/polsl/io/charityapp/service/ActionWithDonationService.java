package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;

import java.util.List;

@Service
public class ActionWithDonationService {
    private final CharityActionService charityActionService;
    private final DonationService donationService;

    @Autowired
    public ActionWithDonationService(CharityActionService charityActionService, DonationService donationService) {
        this.charityActionService = charityActionService;
        this.donationService = donationService;
    }

    public CharityActionReadModel getCharityActionByName(String name) {
        CharityActionReadModel action = charityActionService.getCharityActionByName(name);
        action.setRaised(donationService.getAmountRaisedForAction(name));
        action.setTopDonors(donationService.getTopPublicDonors(name));
        return action;
    }

    public CharityActionReadModel getCharityActionById(Long id) {
        return charityActionService.getCharityActionById(id);
    }

    public List<CharityActionReadModel> getAllActions() {
        List<CharityActionReadModel> actions = charityActionService.getAllActions();
        for (CharityActionReadModel action : actions) {
            action.setRaised(donationService.getAmountRaisedForAction(action.getName()));
        }

        return actions;
    }

    public List<CharityActionReadModel> getOpenCharityActions() {
        List<CharityActionReadModel> actions = charityActionService.getOpenCharityActions();
        for (CharityActionReadModel action : actions) {
            action.setRaised(donationService.getAmountRaisedForAction(action.getName()));
        }

        return actions;
    }
}
