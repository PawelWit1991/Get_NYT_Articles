package pl.pawit.NYT.Articles.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sections")
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Set<Article> articles;

    public void toUpdate(final Section section){
        setName(section.getName());
    }


}
