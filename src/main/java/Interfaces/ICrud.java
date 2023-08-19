package Interfaces;
import java.util.List;
public interface ICrud <T>{
    boolean insert(T t);
    boolean update(T t);
    T selectById(String id);
    List<T> selectAll();
    boolean delete(String id);
}
