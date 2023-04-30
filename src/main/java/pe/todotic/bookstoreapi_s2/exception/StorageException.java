package pe.todotic.bookstoreapi_s2.exception;

public class StorageException extends RuntimeException{

    public  StorageException (String messsage){
        super(messsage);
    }
    public StorageException(String message,Throwable ex){
        super(message,ex);
    }

}
