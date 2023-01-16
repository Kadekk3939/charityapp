package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.ApplicationToCharityMapper;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.repository.ApplicationToCharityActionRepository;
import pl.polsl.io.charityapp.utility.ApplicationStatus;
import pl.polsl.io.charityapp.utility.CurrentUserData;

import java.util.Currency;

@Service
public class ApplicationToCharityActionService {
    private final ApplicationToCharityActionRepository applicationToCharityActionRepository;

    private final ApplicationToCharityMapper applicationToCharityMapper;

    private final CharityActionService charityActionService;

    private final UserService userService;

    @Autowired
    public ApplicationToCharityActionService(ApplicationToCharityActionRepository applicationToCharityActionRepository, ApplicationToCharityMapper applicationToCharityMapper, CharityActionService charityActionService, UserService userService) {
        this.applicationToCharityActionRepository = applicationToCharityActionRepository;
        this.applicationToCharityMapper = applicationToCharityMapper;
        this.charityActionService = charityActionService;
        this.userService = userService;
    }



    public ApplicationToCharityActionReadModel addApplication(ApplicationToCharityActionWriteModel application) {
        //TODO: fix something (doesnt read values)

        ApplicationToCharityAction newApp = applicationToCharityMapper.toEntity(application);
        CharityAction action = charityActionService.getCharityActionEntityByName(application.getCharityActionName());
        User currentUser = userService.getUserEntityByLogin(CurrentUserData.getCurrentUserLogin());

        newApp.setBenefactorId(currentUser);
        newApp.setCharityActionId(action);
        newApp.setStatus(ApplicationStatus.UNCHECKED);


        return applicationToCharityMapper.toReadModel(newApp);
    }

}
