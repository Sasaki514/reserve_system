package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reserve")
@NamedQueries({
        @NamedQuery(name = "getUserReservation", query = "SELECT r FROM Reserve AS r WHERE r.member_id = :member_id"),
        @NamedQuery(name = "getAllReservations", query = "SELECT r FROM Reserve AS r ORDER BY r.reserved_at ASC")
})

@Entity
public class Reserve {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private String member_id;

    @Column(name = "reserved_at", nullable = false)
    private Timestamp reserved_at;

    @Column(name = "required_time", nullable = false)
    private Integer required_time;

    @Column(name = "applied_at", nullable = false)
    private Timestamp applied_at;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Timestamp getReserved_at() {
        return reserved_at;
    }

    public void setReserved_at(Timestamp reserved_at) {
        this.reserved_at = reserved_at;
    }

    public Integer getRequired_time() {
        return required_time;
    }

    public void setRequired_time(Integer required_time) {
        this.required_time = required_time;
    }

    public Timestamp getApplied_at() {
        return applied_at;
    }

    public void setApplied_at(Timestamp applied_at) {
        this.applied_at = applied_at;
    }

}
