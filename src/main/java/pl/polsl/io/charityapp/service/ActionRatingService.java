package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.ActionRatingMapper;
import pl.polsl.io.charityapp.mappers.CharityActionMapper;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ActionRatingWriteModel;
import pl.polsl.io.charityapp.model.entity.ActionRating;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.repository.ActionRatingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionRatingService {
    private final ActionRatingRepository actionRatingRepository;
    private final ActionRatingMapper actionRatingMapper;
    private final CharityActionMapper charityActionMapper;
    private final UserService userService;
    private final CharityActionService charityActionService;
    private final ApplicationToCharityActionService applicationToCharityActionService;

    @Autowired
    public ActionRatingService(ActionRatingRepository actionRatingRepository, ActionRatingMapper actionRatingMapper, CharityActionMapper charityActionMapper, UserService userService, CharityActionService charityActionService, ApplicationToCharityActionService applicationToCharityActionService) {
        this.actionRatingRepository = actionRatingRepository;
        this.actionRatingMapper = actionRatingMapper;
        this.charityActionMapper = charityActionMapper;
        this.userService = userService;
        this.charityActionService = charityActionService;
        this.applicationToCharityActionService = applicationToCharityActionService;
    }

    public void addActionRating(ActionRatingWriteModel actionRatingWriteModel) {
        actionRatingRepository.save(actionRatingMapper.toEntity(userService, charityActionService, actionRatingWriteModel));
    }


    public List<CharityActionReadModel> getRateableActionsForCurrentUser() {
        User user = userService.getLoggedUserEntity();

        List<CharityActionReadModel> closedCharityActions = charityActionService.getClosedCharityActions();
        List<CharityActionReadModel> userCharityActions = charityActionMapper.map(applicationToCharityActionService.getCurrentUserAcceptedActions());
        List<CharityActionReadModel> alreadyRatedCharityActions = charityActionMapper.map(actionRatingRepository.findAllByBenefactorId(user)
                .stream().map(ActionRating::getCharityActionId).collect(Collectors.toList()));

        userCharityActions.retainAll(closedCharityActions);
        userCharityActions.removeAll(alreadyRatedCharityActions);

        return userCharityActions;
    }


}
