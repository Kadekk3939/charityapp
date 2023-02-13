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
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public Long addApplication(ApplicationToCharityActionWriteModel application) {
        ApplicationToCharityAction newApp = applicationToCharityMapper.toEntity(application);
        CharityAction action = charityActionService.getCharityActionEntityByName(application.getCharityActionName());
        User currentUser = userService.getLoggedUserEntity();

        newApp.setBenefactorId(currentUser);
        newApp.setCharityActionId(action);
        newApp.setStatus(ApplicationStatus.UNCHECKED);

        return applicationToCharityActionRepository.save(newApp).getApplicationId();
    }

    public ApplicationToCharityActionReadModel addDocumentsToApplication() {
        return null;
    }

    public List<ApplicationToCharityActionReadModel> getCurrentUserApplications() {
        User currentUser = userService.getLoggedUserEntity();
        List<ApplicationToCharityAction> applications = applicationToCharityActionRepository.findAllByBenefactorId(currentUser);
        return applicationToCharityMapper.map(applications);
    }

    public ApplicationToCharityActionReadModel getRandomUncheckedApplication() {
        Random random = new Random();
        List<ApplicationToCharityAction> applications = applicationToCharityActionRepository.findAllByStatus(ApplicationStatus.UNCHECKED);
        //TODO: error when no unchecked applications
        return applicationToCharityMapper.toReadModel(applications.get(random.nextInt(applications.size())));
    }

    public ApplicationStatus getUserApplication2Action(String benefactorLogin, String actionName) {
        User user = userService.getUserEntityByLogin(benefactorLogin);
        CharityAction charityAction = charityActionService.getCharityActionEntityByName(actionName);

        Optional<ApplicationToCharityAction> application = applicationToCharityActionRepository.findFirstByBenefactorIdAndCharityActionIdOrderByLastUpdatedDesc(user, charityAction);

        return application.map(ApplicationToCharityAction::getStatus).orElse(null);
    }

    public ApplicationToCharityAction getApplicationEntityById(Long applicationId) {
//        Optional<ApplicationToCharityAction> app = applicationToCharityActionRepository.
        return null;
    }

}
