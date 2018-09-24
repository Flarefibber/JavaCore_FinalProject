package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * The CollectionMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Collection obj and IJsonWriter writer
 * Writes the Collection obj to the writer, creates the JsonMapper children
 */

public class CollectionMapper extends AbstractJsonMapper<Collection>{
    public CollectionMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }
    @Override
    public void write(Collection obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeArrayBegin();
            for(Object object: obj){
                useReflectionSerializer (object, writer, serializer);
                writer.writeSeparator();
            }
            writer.writeArrayEnd();
        }
    }
}
