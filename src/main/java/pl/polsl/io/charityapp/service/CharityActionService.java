package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.repository.CharityActionRepository;

import java.util.List;

@Service
public class CharityActionService {

    private final CharityActionRepository charityActionRepository;
    @Autowired
    public CharityActionService(CharityActionRepository charityActionRepository) {
        this.charityActionRepository = charityActionRepository;
    }

    public CharityAction addAction(CharityAction charityAction) {
        return charityActionRepository.save(charityAction);
    }

    public List<CharityAction> getAllActions() {
        return charityActionRepository.findAll();
    }
}
