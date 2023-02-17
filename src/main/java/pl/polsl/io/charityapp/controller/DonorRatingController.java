package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.read.DonationReadModel;
import pl.polsl.io.charityapp.model.dto.write.ActionRatingWriteModel;
import pl.polsl.io.charityapp.model.dto.write.DonorRatingWriteModel;
import pl.polsl.io.charityapp.service.ActionRatingService;
import pl.polsl.io.charityapp.service.DonorRatingService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/donor/rating")
public class DonorRatingController {

    private final DonorRatingService donorRatingService;

    @Autowired
    public DonorRatingController(DonorRatingService donorRatingService) {
        this.donorRatingService = donorRatingService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDonorRating(@RequestBody DonorRatingWriteModel donorRatingWriteModel) {
        return new ResponseEntity<>(donorRatingService.addDonorRating(donorRatingWriteModel), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DonationReadModel>> getRateableActionsForCurrentUser() {
        return new ResponseEntity<>(donorRatingService.getUnratedDonations(), HttpStatus.OK);
    }


}
