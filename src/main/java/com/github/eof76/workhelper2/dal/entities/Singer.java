package com.github.eof76.workhelper2.dal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="SINGER")
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL,
                    query = "select s from Singer s"),
        @NamedQuery(name = Singer.FIND_SINGER_BY_ID,
                    query = "select distinct s from Singer s "
                          + "left join fetch s.albums a "
                          + "left join fetch s.instruments i "
                          + "where s.id = :id"),
        @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
                query = "select distinct s from Singer s "
                      + "left join fetch s.albums a "
                      + "left join fetch s.instruments i ")
})
@SqlResultSetMapping(
        name = "singerResult",
        entities = @EntityResult(entityClass = Singer.class)
)
public class Singer implements Serializable {
    public static final String FIND_ALL = "Singer.findAll";
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT",
        joinColumns = @JoinColumn(name = "SINGER_ID"),
        inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    public Set<Album> getAlbums() {
        return albums;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public String toString()
    {
        return String.format("Singer: Id = %d, FirstName='%s', LastName='%s', BirthDay='%s'",
                id, firstName, lastName, birthDate);
    }
}
