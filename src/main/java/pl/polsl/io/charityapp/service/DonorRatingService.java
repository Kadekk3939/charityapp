package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.DonationMapper;
import pl.polsl.io.charityapp.mappers.DonorRatingMapper;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.DonorRatingWriteModel;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.repository.DonationRepository;
import pl.polsl.io.charityapp.repository.DonorRatingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorRatingService {
    private final DonationRepository donationRepository;

    private final DonorRatingRepository donorRatingRepository;

    private final DonorRatingMapper donorRatingMapper;

    private final DonationMapper donationMapper;

    private final UserService userService;

    private final CharityActionService charityActionService;

    @Autowired
    public DonorRatingService(DonationRepository donationRepository, DonorRatingRepository donorRatingRepository, DonorRatingMapper donorRatingMapper, DonationMapper donationMapper, UserService userService, CharityActionService charityActionService) {
        this.donationRepository = donationRepository;
        this.donorRatingRepository = donorRatingRepository;
        this.donorRatingMapper = donorRatingMapper;
        this.donationMapper = donationMapper;
        this.userService = userService;
        this.charityActionService = charityActionService;
    }

    public List<DonationReadModel> getUnratedDonations() {
        List<Donation> donations = donationRepository.getAllDonations();
        return donationMapper.map(
                donations.stream().filter(donation ->
                        !donorRatingRepository.existsByDonorIdAndCharityActionId(donation.getDonorId(), donation.getCharityActionId()))
                        .collect(Collectors.toList())
        );
    }

    public String addDonorRating(DonorRatingWriteModel donorRatingWriteModel) {
        donorRatingRepository.save(donorRatingMapper.toEntity(charityActionService, userService, donorRatingWriteModel));
        return "OK";
    }

}
