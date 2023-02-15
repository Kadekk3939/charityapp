package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.Document;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAll();

    List<Document> findByFileName(String fileName);

    List<Document> findAllByApplicationToCharityActionId(Long applicationToCharityActionId);

}
