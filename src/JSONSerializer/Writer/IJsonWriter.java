package JSONSerializer.Writer;

/**
 * Created by Вова on 22.07.2017.
 */
public interface IJsonWriter{
    void writeObjectBegin();
    void writeObjectEnd();
    void writeArrayBegin();
    void writeArrayEnd();
    void writeString(String string);
    void writeNumber(Number number);
    void writeSeparator();
    void writePropertySeparator();
    void writeBoolean(boolean obj);
    void writeNull();
    void flush();
    void close();
}
