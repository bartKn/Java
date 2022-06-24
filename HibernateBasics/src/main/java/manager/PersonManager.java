package manager;

import entity.PersonEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class PersonManager {

    private final EntityManager manager;
    Scanner scanner = new Scanner(System.in);

    public PersonManager(EntityManager manager) {
        this.manager = manager;
    }

    public List<PersonEntity> getPersonByName(String name) {
        return manager.createNamedQuery("PersonEntity.ByName", PersonEntity.class).setParameter(1, name).getResultList();
    }

    public List<PersonEntity> getPersonBySurname(String surname) {
        return manager.createNamedQuery("PersonEntity.BySurname", PersonEntity.class).setParameter(1, surname).getResultList();
    }

    public List<PersonEntity> getPersonByState(String state) {
        return manager.createNamedQuery("PersonEntity.ByState", PersonEntity.class).setParameter(1, state).getResultList();
    }

    public List<PersonEntity> getPersonByCountry(String country) {
        return manager.createNamedQuery("PersonEntity.ByCountry", PersonEntity.class).setParameter(1, country).getResultList();
    }

    public List<PersonEntity> getPersonByTown(String town) {
        return manager.createNamedQuery("PersonEntity.ByTown", PersonEntity.class).setParameter(1, town).getResultList();
    }

    public List<PersonEntity> getPersonByEmail(String email) {
        return manager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class).setParameter(1, email).getResultList();
    }

    public void updatePersonEntity(int id) {
        Session session = manager.unwrap(Session.class);
        List<PersonEntity> tempList = session.createNamedQuery("PersonEntity.ById", PersonEntity.class).setParameter(1, id).getResultList();
        if (!tempList.isEmpty()) {
            PersonEntity p = tempList.get(0);
            System.out.println("1. Name\n2. Surname\n3. Email");
            System.out.print("> ");
            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        System.out.print("New name: ");
                        p.setpName(scanner.nextLine());
                        session.update(p);
                        break;
                    case 2:
                        System.out.print("New surname: ");
                        p.setpSurname(scanner.nextLine());
                        session.update(p);
                        break;
                    case 3:
                        System.out.print("New email: ");
                        p.setpEmail(scanner.nextLine());
                        session.update(p);
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Person with given ID doesn't exist");
        }
    }

    public void deletePersonEntity(int id) {
        Session session = manager.unwrap(Session.class);
        List<PersonEntity> tempList = session.createNamedQuery("PersonEntity.ById", PersonEntity.class).setParameter(1, id).getResultList();
        if (!tempList.isEmpty()) {
            manager.remove(tempList.get(0));
            manager.flush();
            manager.clear();
        } else {
            System.out.println("Person with given ID doesn't exist");
        }
    }


}
