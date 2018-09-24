package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

/**
 * The StringMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Object obj and IJsonWriter writer
 * Records Object obj in writer
 */

public class StringMapper extends AbstractJsonMapper<Object>{
    public StringMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeString("" + obj);
        }
    }
}
