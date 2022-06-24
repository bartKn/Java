package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "person", schema = "labhibernate")
@NamedQuery(name = "PersonEntity.ByName", query = "select p from PersonEntity p where p.pName=?1")
@NamedQuery(name = "PersonEntity.BySurname", query = "select p from PersonEntity p where p.pSurname=?1")
@NamedQuery(name = "PersonEntity.ByState", query = "select p from PersonEntity p, AddressInfoEntity a where a.state=?1 and a.personByAiPId=p")
@NamedQuery(name = "PersonEntity.ByCountry", query = "select p from PersonEntity p, AddressInfoEntity a where a.country=?1 and a.personByAiPId=p")
@NamedQuery(name = "PersonEntity.ByTown", query = "select p from PersonEntity p, AddressInfoEntity a where a.town=?1 and a.personByAiPId=p")
@NamedQuery(name = "PersonEntity.ByEmail", query = "select p from PersonEntity p where p.pEmail=?1")
@NamedQuery(name = "PersonEntity.ById", query = "select p from PersonEntity p where p.pId=?1")
@NamedQuery(name = "deletePersonEntity.ByPersonId", query = "delete from PersonEntity p where p.pId=?1")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_id")
    private int pId;
    @Basic
    @Column(name = "p_name")
    private String pName;
    @Basic
    @Column(name = "p_surname")
    private String pSurname;
    @Basic
    @Column(name = "p_email")
    private String pEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personByAiPId", fetch = FetchType.EAGER)
    private Collection<AddressInfoEntity> addressInfosByPId;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSurname() {
        return pSurname;
    }

    public void setpSurname(String pSurname) {
        this.pSurname = pSurname;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (pId != that.pId) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (pSurname != null ? !pSurname.equals(that.pSurname) : that.pSurname != null) return false;
        if (pEmail != null ? !pEmail.equals(that.pEmail) : that.pEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pId;
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (pSurname != null ? pSurname.hashCode() : 0);
        result = 31 * result + (pEmail != null ? pEmail.hashCode() : 0);
        return result;
    }

    public Collection<AddressInfoEntity> getAddressInfosByPId() {
        return addressInfosByPId;
    }

    public void setAddressInfosByPId(Collection<AddressInfoEntity> addressInfosByPId) {
        this.addressInfosByPId = addressInfosByPId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OSOBA: ")
                .append(pName).append(" ")
                .append(pSurname).append(" ")
                .append(pEmail).append(" ")
                .append("ID = ").append(pId).append("\n")
                .append("\tZnane adresy:\n");
        addressInfosByPId.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
