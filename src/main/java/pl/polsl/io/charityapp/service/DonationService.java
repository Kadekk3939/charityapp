package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.DonationMapper;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.repository.DonationRepository;

import java.util.Collections;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;
    private final CharityActionService charityActionService;
    private final UserService userService;

    @Autowired
    public DonationService(DonationRepository donationRepository, DonationMapper donationMapper, CharityActionService charityActionService, UserService userService) {
        this.donationRepository = donationRepository;
        this.donationMapper = donationMapper;
        this.charityActionService = charityActionService;
        this.userService = userService;
    }
    public DonationReadModel addDonation(DonationWriteModel donationWriteModel) {
        Donation donation = donationMapper.toEntity(donationWriteModel, charityActionService, userService);
        // evens are confirmed, odds - not
        donation.setPaymentConfirmed(Math.round(donation.getAmount()) % 2 == 0);
        return donationMapper.toReadModel(donationRepository.save(donation));
    }

    public List<DonationReadModel> getCurrentUserDonations() {
        User user = userService.getLoggedUserEntity();
        List<Donation> donations = donationRepository.findAllByDonorIdOrderByDonationIdDesc(user);
        return donationMapper.map(donations);
    }

    public Float getAmountRaisedForAction(String actionName) {
        CharityAction action = charityActionService.getCharityActionEntityByName(actionName);
        Float raised = donationRepository.getRaisedAmount(action);
        return raised == null ? 0 : raised;
    }

    public List<DonationReadModel> getTopPublicDonors(String actionName) {
        CharityAction action = charityActionService.getCharityActionEntityByName(actionName);
        List<Donation> donations = donationRepository.getGroupedDonations(action);
        if (donations == null) {
            return Collections.emptyList();
        }
        donations.forEach(donation -> donation.setCharityActionId(action));
        return donationMapper.map(donations.subList(0, Math.min(donations.size(), 3)));
    }
}
