package JSONSerializer.Mapper;

import JSONSerializer.Annotation.JsonIgnore;
import JSONSerializer.Annotation.JsonProperty;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * The CustomMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Object obj and IJsonWriter writer
 * Disassembles custom objects and writes to writer, creates the JsonMapper children, uses annotations
 */

public class CustomMapper extends AbstractJsonMapper<Object>{
    public CustomMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeObjectBegin();
            ArrayList<Field> fields = getSerializeFields(obj);
            for(Field field: fields){
                try {
                    String fieldName = (String)field.getName();
                    String newName = null;
                    Annotation annotation = field.getAnnotation(JsonProperty.class);
                    if(annotation != null){
                        Method method = annotation.getClass().getDeclaredMethod("name");
                        if(method != null)
                            newName = (String) method.invoke(annotation);
                    }

                    if(newName != null && newName.length() != 0){
                        fieldName = newName;
                    }
                    Object fieldValue = field.get(obj);
                    writer.writeString(fieldName);
                    writer.writePropertySeparator();
                    useReflectionSerializer (fieldValue, writer, serializer);
                    writer.writeSeparator();
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            writer.writeObjectEnd();

        }
    }
    private ArrayList<Field> getSerializeFields(Object obj){

        Field[] allFields = obj.getClass().getDeclaredFields();
        ArrayList<Field> res = new ArrayList<>();
        for (Field field :allFields) {
            if(!hasAnnotation(field, JsonIgnore.class.getCanonicalName())
                    && Modifier.isPublic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())){
                res.add(field);
            }
            Annotation[] annotations = field.getAnnotations();
            for (Annotation an : annotations) {
                if (an.annotationType().getCanonicalName().equals(JsonProperty.class.getCanonicalName())){
                    field.setAccessible(true);
                    res.add(field);
                }
            }
        }
        return res;
    }
    private boolean hasAnnotation(Field field, String annotation){
        Annotation[] annotations = field.getAnnotations();
        for (Annotation an : annotations) {
            if (an.annotationType().getCanonicalName().equals(annotation)){
                return true;
            }
        }
        return false;
    }
}
