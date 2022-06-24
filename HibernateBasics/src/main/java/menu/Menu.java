package menu;

import entity.EntityCreator;
import manager.AddressManager;
import manager.PersonManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Menu {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    EntityCreator creator = new EntityCreator(manager);
    PersonManager personManager = new PersonManager(manager);
    AddressManager addressManager = new AddressManager(manager);
    Scanner scanner = new Scanner(System.in);



    public void start() {
        try {
            while (true) {
                try {
                    System.out.println("1. add\n2. read\n3. modify\n4. delete\n0. exit");
                    System.out.print("> ");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            add();
                            break;
                        case 2:
                            read();
                            break;
                        case 3:
                            modify();
                            break;
                        case 4:
                            delete();
                            break;
                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Wrong input");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } finally {
            if (transaction.isActive())
                transaction.rollback();
            manager.close();
        }
    }

    private void add() {
        System.out.println("1. Person\n2. Address");
        System.out.print("> ");

        transaction.begin();

        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    manager.persist(creator.createPersonEntity());
                    break;
                case 2:
                    manager.persist(creator.createAddressInfoEntity());
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

        transaction.commit();
    }

    private void read() {
        System.out.println("Find person by:\n1. Name\n2. Surname\n3. State\n4. Country\n5. Town\n6. Email");
        System.out.print("> ");

        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.print("Name: ");
                    personManager.getPersonByName(scanner.nextLine()).forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Surname: ");
                    personManager.getPersonBySurname(scanner.nextLine()).forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("State: ");
                    personManager.getPersonByState(scanner.nextLine()).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Country: ");
                    personManager.getPersonByCountry(scanner.nextLine()).forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Town: ");
                    personManager.getPersonByTown(scanner.nextLine()).forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Email: ");
                    personManager.getPersonByEmail(scanner.nextLine()).forEach(System.out::println);
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void modify() {
        System.out.println("1. Person\n2. Address");
        System.out.print("> ");

        transaction.begin();

        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.print("ID = ");
                    personManager.updatePersonEntity(scanner.nextInt());
                    break;
                case 2:
                    System.out.print("ID = ");
                    addressManager.updateAddressInfoEntity(scanner.nextInt());
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        transaction.commit();
    }

    private void delete() {
        System.out.println("1. Person\n2. Address");
        System.out.print("> ");

        transaction.begin();

        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.print("ID = ");
                    personManager.deletePersonEntity(scanner.nextInt());
                    break;
                case 2:
                    System.out.print("ID = ");
                    addressManager.deleteAddressInfoEntity(scanner.nextInt());
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        transaction.commit();
    }
}
