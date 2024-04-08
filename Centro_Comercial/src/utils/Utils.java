package utils;

import java.util.List;

public class Utils {
    public static <T> T[] listToArray(List<T> list){
        //Crear un arreglo de Object del tamaño de la lista
        T[] array = (T[]) new Object[list.size()];

        int i = 0;
        for (T iterador : list){
            array[i++] = iterador;
        }

        return array;
    }
}
