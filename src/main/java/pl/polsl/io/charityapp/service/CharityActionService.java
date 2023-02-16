package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.CharityActionMapper;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.repository.CharityActionRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CharityActionService {
    private final CharityActionRepository charityActionRepository;
    private final CharityActionMapper charityActionMapper;
    @Autowired
    public CharityActionService(CharityActionRepository charityActionRepository, CharityActionMapper charityActionMapper) {
        this.charityActionRepository = charityActionRepository;
        this.charityActionMapper = charityActionMapper;
    }

    public CharityAction addCharityAction(CharityActionWriteModel charityActionWriteModel) {
        CharityAction charityAction = charityActionMapper.toEntity(charityActionWriteModel);
        return charityActionRepository.save(charityAction);
    }

    public CharityActionReadModel getCharityActionByName(String name) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByName(name);

        return charityAction.map(charityActionMapper::toLongReadModel).orElse(null);
    }

    public CharityAction getCharityActionEntityByName(String name) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByName(name);
        return charityAction.orElse(null);
    }

    public CharityActionReadModel getCharityActionById(Long id) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByActionId(id);

        return charityAction.map(charityActionMapper::toLongReadModel).orElse(null);
    }

    public List<CharityActionReadModel> getAllActions() {
        return charityActionMapper.map(charityActionRepository.findAll());
    }

    public Long getActionIdByName(String name) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByName(name);

        return charityAction.map(CharityAction::getActionId).orElse(null);
    }

    public List<CharityActionReadModel> getOpenCharityActions() {
        Date tomorrow = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
        return charityActionMapper.map(charityActionRepository.findAllByClosedEarlyIsFalseAndEndDateGreaterThanOrderByEndDateAsc(tomorrow));
    }

    public List<CharityActionReadModel> getClosedCharityActions() {
        Date tomorrow = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
        List<CharityAction> open =  charityActionRepository.findAllByClosedEarlyIsFalseAndEndDateGreaterThanOrderByEndDateAsc(tomorrow);
        List<CharityAction> all = charityActionRepository.findAll();
        all.removeAll(open);
        return charityActionMapper.map(all);
    }

    public Boolean closeAction(String name) {
        CharityAction action = this.getCharityActionEntityByName(name);
        if (action.getClosedEarly()) {
            return false;
        }
        action.setClosedEarly(true);
        charityActionRepository.save(action);
        return true;
    }

    public CharityAction save(CharityAction action) {
        return charityActionRepository.save(action);
    }
}
