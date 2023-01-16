package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.CharityActionMapper;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.repository.CharityActionRepository;

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

    //TODO: change to toEntity()
    public CharityAction addCharityAction(CharityActionWriteModel charityActionWriteModel) {
        CharityAction charityAction = new CharityAction();
        charityActionMapper.updateCharityActionFromDto(charityActionWriteModel, charityAction);
        return charityActionRepository.save(charityAction);
    }

    public CharityActionReadModel getCharityActionByName(String name) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByName(name);

        return charityAction.map(charityActionMapper::toShortReadModel).orElse(null);
    }

    public CharityActionReadModel getCharityActionById(Long id) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByActionId(id);

        return charityAction.map(charityActionMapper::toShortReadModel).orElse(null);
    }

    public List<CharityActionReadModel> getAllActions() {
        return charityActionMapper.map(charityActionRepository.findAll());
    }

    public Long getActionIdByName(String name) {
        Optional<CharityAction> charityAction = charityActionRepository.findCharityActionByName(name);

        return charityAction.map(CharityAction::getActionId).orElse(null);
    }
}
