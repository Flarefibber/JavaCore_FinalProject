package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

import java.lang.reflect.InvocationTargetException;

/**
 * The ObjectArrayMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Object[] obj and IJsonWriter writer
 * Writes the Object[] obj to the writer, creates the JsonMapper children
 */

public class ObjectArrayMapper extends AbstractJsonMapper<Object[]>{
    public ObjectArrayMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }
    @Override
    public void write(Object[] obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeArrayBegin();
            for(int i = 0; i < obj.length; i++){
                Object object = obj[i];
                useReflectionSerializer (object, writer, serializer);
                writer.writeSeparator();
            }
            writer.writeArrayEnd();
        }
    }
}
