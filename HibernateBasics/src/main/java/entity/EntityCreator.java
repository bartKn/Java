package entity;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityCreator {
    private final EntityManager manager;
    Scanner scanner = new Scanner(System.in);

    public EntityCreator(EntityManager manager) {
        this.manager = manager;
    }

    public PersonEntity createPersonEntity() {
        PersonEntity person = new PersonEntity();

        System.out.print("Name: ");
        person.setpName(scanner.nextLine());

        System.out.print("Surname: ");
        person.setpSurname(scanner.nextLine());

        System.out.print("Email: ");
        person.setpEmail(scanner.nextLine());

        person.setAddressInfosByPId(new ArrayList<AddressInfoEntity>());

        return person;
    }

    public AddressInfoEntity createAddressInfoEntity() {
        AddressInfoEntity address = new AddressInfoEntity();

        System.out.print("Street: ");
        address.setStreet(scanner.nextLine());

        System.out.print("House number: ");
        address.setHouseNumber(scanner.nextLine());

        System.out.print("Town: ");
        address.setTown(scanner.nextLine());

        System.out.print("State: ");
        address.setState(scanner.nextLine());

        System.out.print("Country: ");
        address.setCountry(scanner.nextLine());

        System.out.print("Owner ID: ");
        int id = scanner.nextInt();

        List<PersonEntity> temp = manager.createNamedQuery("PersonEntity.ById", PersonEntity.class).setParameter(1, id).getResultList();

        if (temp.size() > 0) {
            PersonEntity person = temp.get(0);

            person.getAddressInfosByPId().add(address);
            address.setPersonByAiPId(person);
        }

        return address;
    }
}
