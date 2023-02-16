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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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

    public ApplicationToCharityAction save(ApplicationToCharityAction application) {
        return applicationToCharityActionRepository.save(application);
    }

    public List<ApplicationToCharityActionReadModel> getCurrentUserApplications() {
        User currentUser = userService.getLoggedUserEntity();
        List<ApplicationToCharityAction> applications = applicationToCharityActionRepository.findAllByBenefactorId(currentUser);
        return applicationToCharityMapper.map(applications);
    }

    public Long getRandomUncheckedOrPreviouslyChosenApplication() {
        User currentUser = userService.getLoggedUserEntity();
        Optional<ApplicationToCharityAction> application = applicationToCharityActionRepository.findByVerifiedByAndStatus(currentUser, ApplicationStatus.UNCHECKED);
        // worker already has application to process
        if (application.isPresent()) {
            return application.get().getApplicationId();
        }
        Random random = new Random();
        List<ApplicationToCharityAction> applications = applicationToCharityActionRepository.findAllByStatusAndVerifiedByIsNull(ApplicationStatus.UNCHECKED);
        // no applications to process
        if (applications == null || applications.isEmpty()) {
            return null;
        }
        // random application
        ApplicationToCharityAction app = applications.get(random.nextInt(applications.size()));
        app.setVerifiedBy(currentUser);
        applicationToCharityActionRepository.save(app);
        return app.getApplicationId();
    }

    public ApplicationStatus getUserApplication2Action(String benefactorLogin, String actionName) {
        User user = userService.getUserEntityByLogin(benefactorLogin);
        CharityAction charityAction = charityActionService.getCharityActionEntityByName(actionName);

        Optional<ApplicationToCharityAction> application = applicationToCharityActionRepository.findFirstByBenefactorIdAndCharityActionIdOrderByLastUpdatedDesc(user, charityAction);

        return application.map(ApplicationToCharityAction::getStatus).orElse(null);
    }

    public ApplicationToCharityAction getApplicationEntityById(Long applicationId) {
        Optional<ApplicationToCharityAction> app = applicationToCharityActionRepository.findById(applicationId);
        return app.orElse(null);
    }

    public ApplicationToCharityActionReadModel getApplicationById(Long applicationId) {
        ApplicationToCharityAction app = getApplicationEntityById(applicationId);
        return applicationToCharityMapper.toReadModel(app);
    }

    public String processVerdict(Long applicationId, String verdict) {
        ApplicationToCharityAction app = getApplicationEntityById(applicationId);
        String result = null;
        switch (verdict) {
            case "ACCEPTED":
                app.setStatus(ApplicationStatus.ACCEPTED);
                result = "OK";
                break;
            case "REJECTED":
                app.setStatus(ApplicationStatus.REJECTED);
                result = "OK";
                break;
            default:
                result = "ERROR";
        }
        applicationToCharityActionRepository.save(app);
        return result;
    }

    public List<CharityAction> getCurrentUserAcceptedActions() {
        User user = userService.getLoggedUserEntity();
        List<ApplicationToCharityAction> apps = applicationToCharityActionRepository.findAllByBenefactorIdAndStatus(user, ApplicationStatus.ACCEPTED);
        return apps.stream().map(ApplicationToCharityAction::getCharityActionId).collect(Collectors.toList());
    }
}
