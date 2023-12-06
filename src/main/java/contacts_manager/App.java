package contacts_manager;

import contacts_manager.dao.FileContactsDAO;
import contacts_manager.models.Contact;
import contacts_manager.utils.Input;
import lombok.Getter;

public class App {
    public static void main(String[] args) {
        Input input = new Input();
        while(true){
            displayMenu();
            int option = input.getInt(1, 5);
            handleOption(option, input);
        }

    }


    private static void displayMenu() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact by name.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
    }

    public static void handleOption(int option, Input input) {
        switch (option){
            case 1:
                FileContactsDAO.readFile();
                break;
            case 2:
                handleAddContact(input);
                break;
            case 3:
                handleSearchContact(input);
                break;
            case 4:
                handleDeleteContact(input);
                break;
            case 5:
                System.exit(0);
        }
    }

    private static void handleAddContact(Input input) {
        String fn = input.getString("Give me the full name");
        String phone = input.getString("Give me the phone number");
        Contact contact = new Contact(fn, phone);
        FileContactsDAO.writeContactToFile(contact);
    }

    private static void handleSearchContact(Input input) {
        String term = input.getString("Give me the name to search");
        FileContactsDAO.search(term);
    }

    private static void handleDeleteContact(Input input) {
        String aNumber = input.getString("Give me the name to delete");
        FileContactsDAO.delete(aNumber);
    }

    @Getter
    public enum MenuOption {
        VIEW_CONTACTS(1),
        ADD_CONTACT(2),
        SEARCH_CONTACT(3),
        DELETE_CONTACT(4),
        EXIT(5);

        private final int value;

        MenuOption(int value) {
            this.value = value;
        }

    }


}


//public class App {
//    public static void main(String[] args) {
//        Input input = new Input();
//
//        while(true){
//            System.out.println("1. View contacts.\n" +
//                    "2. Add a new contact.\n" +
//                    "3. Search a contact by name.\n" +
//                    "4. Delete an existing contact by name.\n" +
//                    "5. Exit.\n" +
//                    "Enter an option (1, 2, 3, 4 or 5):");
//            int option = input.getInt(1, 5);
//            switch (option){
//                case 1:
//                    FileContactsDAO.readFile();
//                    break;
//                case 2:
//                    String fn = input.getString("Give me the full name");
//                    String phone = input.getString("Give me the phone number");
//                    Contact contact = new Contact(fn, phone);
//                    FileContactsDAO.writeContactToFile(contact);
//                    break;
//                case 3:
//                    String term = input.getString("Give me the name to search");
//                    FileContactsDAO.search(term);
//                    break;
//                case 4:
//                    String aNumber = input.getString("Give me the name to delete");
//                    FileContactsDAO.delete(aNumber);
//                    break;
//                case 5:
//                    System.exit(0);
//            }
//
//        }
//
//    }

