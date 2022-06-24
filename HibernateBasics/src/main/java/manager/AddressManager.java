package manager;

import entity.AddressInfoEntity;
import entity.PersonEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class AddressManager {

    private final EntityManager manager;
    private Scanner scanner = new Scanner(System.in);

    public AddressManager(EntityManager manager) {
        this.manager = manager;
    }


    public void updateAddressInfoEntity(int id) {
        Session session = manager.unwrap(Session.class);
        List<AddressInfoEntity> tempList = session.createNamedQuery("AddressInfoEntity.ById", AddressInfoEntity.class).setParameter(1, id).getResultList();
        if (!tempList.isEmpty()) {
            AddressInfoEntity a = tempList.get(0);
            System.out.println("1. Street\n2. House number\n3. Town\n4. State\n5. Country");
            System.out.print("> ");
            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        System.out.print("New street: ");
                        a.setStreet(scanner.nextLine());
                        session.update(a);
                        break;
                    case 2:
                        System.out.print("New house number: ");
                        a.setHouseNumber(scanner.nextLine());
                        session.update(a);
                        break;
                    case 3:
                        System.out.print("New town: ");
                        a.setTown(scanner.nextLine());
                        session.update(a);
                        break;
                    case 4:
                        System.out.print("New state: ");
                        a.setState(scanner.nextLine());
                        session.update(a);
                        break;
                    case 5:
                        System.out.print("New country: ");
                        a.setCountry(scanner.nextLine());
                        session.update(a);
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
    public void deleteAddressInfoEntity(int id) {
        Session session = manager.unwrap(Session.class);
        List<AddressInfoEntity> tempList = session.createNamedQuery("AddressInfoEntity.ById", AddressInfoEntity.class).setParameter(1, id).getResultList();
        if (!tempList.isEmpty()) {
            manager.remove(tempList.get(0));
            manager.flush();
            manager.clear();
        } else {
            System.out.println("Address with given ID doesn't exist");
        }
    }
}
