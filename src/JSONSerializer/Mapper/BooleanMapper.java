package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

/**
 * The BooleanMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Boolean obj and IJsonWriter writer
 * Records Boolean obj in writer
 */

public class BooleanMapper extends AbstractJsonMapper<Boolean>{
    public BooleanMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }
    @Override
    public void write(Boolean obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeBoolean(obj);
        }
    }
}
