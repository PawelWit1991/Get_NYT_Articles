package pl.pawit.NYT.Articles.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sectionType;
    private String title;
    private String abstractField;
    private String byline;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
