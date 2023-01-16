package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.mappers.ApplicationToCharityMapper;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;
import pl.polsl.io.charityapp.repository.ApplicationToCharityActionRepository;

@Service
public class ApplicationToCharityActionService {
    private final ApplicationToCharityActionRepository applicationToCharityActionRepository;

    private final ApplicationToCharityMapper applicationToCharityMapper;

    private final CharityActionService charityActionService;

    @Autowired
    public ApplicationToCharityActionService(ApplicationToCharityActionRepository applicationToCharityActionRepository, ApplicationToCharityMapper applicationToCharityMapper, CharityActionService charityActionService) {
        this.applicationToCharityActionRepository = applicationToCharityActionRepository;
        this.applicationToCharityMapper = applicationToCharityMapper;
        this.charityActionService = charityActionService;
    }


    //TODO: put service dependency in mapper
    public ApplicationToCharityActionReadModel addApplication(ApplicationToCharityActionWriteModel application) {
        ApplicationToCharityAction newApp = applicationToCharityMapper.toEntity(application);
        Long actionId = charityActionService.getActionIdByName(application.getCharityActionName());

    }

}
