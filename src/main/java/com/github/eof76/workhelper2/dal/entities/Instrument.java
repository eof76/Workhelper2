package com.github.eof76.workhelper2.dal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INSTRUMENT")
public class Instrument implements Serializable {
    @Id
    @Column(name = "INSTRUMENT_ID")
    private String instrumentId;

    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT",
        joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    private Set<Singer> singers = new HashSet<>();

    public String getInstrumentId() {
        return instrumentId;
    }

    @Override
    public String toString() {
        return String.format("Instrument: Id='%s'", instrumentId);
    }
}
