package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.DonationMapper;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.repository.DonationRepository;

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

    //TODO: Donation -> DonationReadModel
    public Donation addDonation(DonationWriteModel donationWriteModel) {
        Donation donation = donationMapper.toEntity(donationWriteModel, charityActionService, userService);
        // 50% for paymentConfirmed = true
        donation.setPaymentConfirmed(Math.random() <= 0.5);
        return donationRepository.save(donation);
    }
}
