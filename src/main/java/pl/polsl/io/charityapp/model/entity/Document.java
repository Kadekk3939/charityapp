package pl.polsl.io.charityapp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationToCharityAction applicationToCharityActionId;
}