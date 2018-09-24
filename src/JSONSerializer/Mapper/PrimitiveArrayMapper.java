package JSONSerializer.Mapper;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IJsonWriter;

/**
 * The PrimitiveArrayMapper class inheritor of the AbstractJsonMapper class
 *
 * Overrides the write method
 * write accepts Object obj and IJsonWriter writer
 * Writes the Object obj to the writer, determines the type of the primitive array, and creates the JsonMapper children
 * Works with all primitive data types
 */

public class PrimitiveArrayMapper extends AbstractJsonMapper<Object>{
    public PrimitiveArrayMapper(JsonSerializer serializer) {
        this.serializer=serializer;
    }
    @Override
    public void write(Object obj, IJsonWriter writer) {
        if(nullCheck(obj)){
            writeNull(writer);
        } else {
            writer.writeArrayBegin();

            if(byte[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((byte[])obj).length; i++){
                    useReflectionSerializer (((byte[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(short[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((short[])obj).length; i++){
                    useReflectionSerializer (((short[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(char[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                if(((char[])obj).length > 0){
                    for(Character object : (char[])obj){
                        useReflectionSerializer (object, writer, serializer);
                        writer.writeSeparator();
                    }
                }
            } else if(int[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((int[])obj).length; i++){
                    useReflectionSerializer (((int[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(long[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((long[])obj).length; i++){
                    useReflectionSerializer (((long[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(float[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((float[])obj).length; i++){
                    useReflectionSerializer (((float[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(double[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                for(int i = 0; i < ((double[])obj).length; i++){
                    useReflectionSerializer (((double[])obj)[i], writer, serializer);
                    writer.writeSeparator();
                }
            } else if(boolean[].class.getCanonicalName().equals(obj.getClass().getCanonicalName())) {
                if(((boolean[])obj).length > 0){
                    for(Object object : (boolean[])obj){
                        useReflectionSerializer (object, writer, serializer);
                        writer.writeSeparator();
                    }
                }
            }
            writer.writeArrayEnd();

        }
    }

}
