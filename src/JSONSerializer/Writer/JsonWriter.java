package JSONSerializer.Writer;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JsonWriter implements IJsonWriter{
    protected StringBuilder stringBuilder;
    protected Writer writer;

    public JsonWriter(){
        this.stringBuilder = new StringBuilder();
        this.writer = new StringWriter();
    }

    public JsonWriter (Writer writer){
        this.writer = writer;
        this.stringBuilder = new StringBuilder();
    }

    protected void deleteLastSeparator(){
        if (stringBuilder.charAt(stringBuilder.length()-1) == ','){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }
    @Override
    public void writeObjectBegin() {
            stringBuilder.append('{');
    }

    @Override
    public void writeObjectEnd() {
        deleteLastSeparator();
        stringBuilder.append('}');
        flush();
    }

    @Override
    public void writeArrayBegin(){
        stringBuilder.append('[');
    }

    @Override
    public void writeArrayEnd() {
        deleteLastSeparator();
        stringBuilder.append(']');
        flush();
    }

    @Override
    public void writeBoolean(boolean obj) {
            stringBuilder.append(obj);
    }

    @Override
    public void writeNull() {
            stringBuilder.append("null");

    }

    @Override
    public void writeNumber(Number number) {
            stringBuilder.append(number);

    }



    @Override
    public void writePropertySeparator() {
            stringBuilder.append(':');

    }

    @Override
    public void writeSeparator() {
            stringBuilder.append(',');

    }

    @Override
    public void writeString(String string) {
        if(string.length() <= 2 || (string.charAt(0) != '"' && string.charAt(string.length()-1) != '"')){
            string = '"'+string+'"';
        }
            stringBuilder.append(string);

    }

    @Override
    public void flush(){
        try {
            writer.write(stringBuilder.toString());
            writer.flush();
            stringBuilder = new StringBuilder();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
