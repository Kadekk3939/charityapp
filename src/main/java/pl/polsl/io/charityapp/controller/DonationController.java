package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.DonationWriteModel;
import pl.polsl.io.charityapp.service.DonationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/donation")
public class DonationController {
    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/add")
    public ResponseEntity<DonationReadModel> addDonation(@RequestBody DonationWriteModel donationWriteModel) {
        DonationReadModel donation = donationService.addDonation(donationWriteModel);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DonationReadModel>> getCurrentUserDonations() {
        return new ResponseEntity<>(donationService.getCurrentUserDonations(), HttpStatus.OK);
    }
}
