package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ActionRatingWriteModel;
import pl.polsl.io.charityapp.service.ActionRatingService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rating")
public class ActionRatingController {

    private final ActionRatingService actionRatingService;

    @Autowired
    public ActionRatingController(ActionRatingService actionRatingService) {
        this.actionRatingService = actionRatingService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addActionRating(@RequestBody ActionRatingWriteModel actionRatingWriteModel) {
        actionRatingService.addActionRating(actionRatingWriteModel);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharityActionReadModel>> getRateableActionsForCurrentUser() {
        return new ResponseEntity<>(actionRatingService.getRateableActionsForCurrentUser(), HttpStatus.OK);
    }


}
