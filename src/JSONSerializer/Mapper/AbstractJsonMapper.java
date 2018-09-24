package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract class AbstractJsonMapper
 *
 * Contains an instance of the JsonSerializer class that is received in the constructor
 *
 * abstract method write
 *
 * nullCheck checks <T> obj to null and returns true or false
 *
 * writeNull invokes the writeNull method of the IJsonWriter class
 *
 * useReflectionSerializer calls the serializer method of the JsonSerializer class. Uses reflection, creates a child Mapper
 */

public abstract class AbstractJsonMapper<T>{
    JsonSerializer serializer;

    public abstract void write(T obj, IJsonWriter writer);

    boolean nullCheck (T obj){
        if(obj==null){
            return true;
        } else {return false;}
    }

    protected void writeNull(IJsonWriter writer){
        writer.writeNull();
    }
    protected void useReflectionSerializer (Object object, IJsonWriter writer, JsonSerializer serializer){
        try {
            Method method = serializer.getClass().getDeclaredMethod("serialize",Object.class, IJsonWriter.class);
            method.setAccessible(true);
            method.invoke(serializer,object, writer);
            method.setAccessible(false);
        } catch (NoSuchMethodException e) {
            System.err.println("No such method");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
