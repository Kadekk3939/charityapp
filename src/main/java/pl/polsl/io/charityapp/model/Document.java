package pl.polsl.io.charityapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationToCharityAction applicationToCharityActionId;

    public void OpenFile(){

    }
}