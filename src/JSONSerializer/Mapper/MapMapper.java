package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * The MapMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Map obj and IJsonWriter writer
 * Writes the keys and the value of Map obj to the writer, creates the JsonMapper children
 */

public class MapMapper extends AbstractJsonMapper<Map> {
    public MapMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }
    @Override
    public void write(Map obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeArrayBegin();
            Collection keys = obj.keySet();
            Collection values = obj.values();
            Object[] keyArray = keys.toArray();
            Object[] valueArray = values.toArray();
            for (int i = 0; i < keyArray.length; i++) {
                writer.writeString(keyArray[i].toString());
                writer.writePropertySeparator();
                useReflectionSerializer (valueArray[i], writer, serializer);
                writer.writeSeparator();
            }
            writer.writeArrayEnd();
        }

    }
}
