package manager;

public class ManagerSaveException extends RuntimeException {
    ManagerSaveException(String message){
        super(message);
        System.out.println("Ошибка" + message);
    }
}
