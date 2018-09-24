package JSONSerializer.Controller;

import JSONSerializer.Serializer.JsonSerializer;

import java.io.OutputStream;
import java.nio.charset.Charset;

public class JSController {
    private JsonSerializer jsonSerializer = new JsonSerializer();

    public void setIndent(boolean indent){
        jsonSerializer.setIndent(indent);
    }

    public String serialize(Object obj){
        return jsonSerializer.serialize(obj);
    }

    public void serialize(Object obj, OutputStream stream){
        jsonSerializer.serialize(obj,stream);
    }

    public void serialize(Object obj, OutputStream stream, Charset charset){
        jsonSerializer.serialize(obj,stream,charset);
    }
}
