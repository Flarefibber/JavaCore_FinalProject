package JSONSerializer.Serializer;


import JSONSerializer.Mapper.*;
import JSONSerializer.Writer.IJsonWriter;
import JSONSerializer.Writer.IndentedJsonWriter;
import JSONSerializer.Writer.JsonWriter;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class JsonSerializer {
    private static volatile JsonSerializer instance = new JsonSerializer();
    public boolean indent = false;
    Map<Class, AbstractJsonMapper> mappersCache;

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public JsonSerializer() {
        this.mappersCache = new HashMap<>();
        this.mappersCache.put(Boolean.class, new BooleanMapper(this));
        this.mappersCache.put(String.class, new StringMapper(this));
        this.mappersCache.put(Collection.class, new CollectionMapper(this));
        this.mappersCache.put(Number.class, new NumberMapper(this));
        this.mappersCache.put(Object[].class, new ObjectArrayMapper(this));
        this.mappersCache.put(Map.class, new MapMapper(this));
        this.mappersCache.put(Array.class, new PrimitiveArrayMapper(this));
        this.mappersCache.put(Object.class, new CustomMapper(this));
    }

    public static JsonSerializer getInstance() {
        return instance;
    }
    public boolean isIndent(){
        return indent;
    }

    public void setIndent(boolean indent){
        this.indent = indent;
    }

    public String serialize(Object obj) throws IllegalStateException {
        StringWriter  stream = new StringWriter ();
        serialize(obj, stream);

        return stream.toString();
    }

    public void serialize(Object obj, OutputStream stream){

        serialize(obj,stream, DEFAULT_CHARSET);
    }

    public void serialize(Object obj, OutputStream stream, Charset charset){
        serialize(obj, new OutputStreamWriter(stream, charset));
    }

    public void serialize(Object obj, Writer writer){
        JsonWriter jsonWriter;
        if(isIndent()) {
            jsonWriter = new IndentedJsonWriter(writer);
        } else {
            jsonWriter = new JsonWriter(writer);
        }
        if(obj == null){
            jsonWriter.writeNull();
            return;
        }
        serialize(obj,jsonWriter);
    }

    protected void serialize(Object object, IJsonWriter writer){
        if(object == null) {
            writer.writeNull();
        } else {
           AbstractJsonMapper mapper = getMapper(object.getClass());
            mapper.write(object, writer);
        }
        writer.close();
    }

protected AbstractJsonMapper getMapper(Class clazz) {
        if (Number.class.isAssignableFrom(clazz)) {
            return mappersCache.get(Number.class);
        } else if (clazz.isArray() && clazz.getComponentType().isPrimitive()) {
            return mappersCache.get(Array.class);
        } else if (Map.class.isAssignableFrom(clazz)) {
            return mappersCache.get(Map.class);
        } else if(String.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz)){
            return mappersCache.get(String.class);
        } else if (mappersCache.containsKey(clazz)) {
            return mappersCache.get(clazz);
        } else if (clazz.isArray()) {
            return mappersCache.get(Object[].class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return mappersCache.get(Collection.class);
        }

        return mappersCache.get(Object.class);
    }
}

