package pl.polsl.io.charityapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.repository.CharityActionRepository;
import pl.polsl.io.charityapp.service.CharityActionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/action")
public class CharityActionController {
    private final CharityActionService charityActionService;
    private final CharityActionRepository charityActionRepository;

    public CharityActionController(CharityActionService charityActionService, CharityActionRepository charityActionRepository) {
        this.charityActionService = charityActionService;
        this.charityActionRepository = charityActionRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<CharityAction>> getAllCharityActions(){
        List<CharityAction> listOfCharityActions = charityActionService.getAllActions();
        return new ResponseEntity<>(listOfCharityActions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CharityAction> addCharityAction(@RequestBody CharityAction charityAction){
        CharityAction newCharityAction = charityActionService.addAction(charityAction);
        return new ResponseEntity<>(newCharityAction, HttpStatus.CREATED);
    }
    // ocena aplikacji beneficjenta
//    @PutMapping("/{userName}/{actionName}/{result}")
}
