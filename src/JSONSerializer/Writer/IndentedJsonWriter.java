package JSONSerializer.Writer;

import java.io.Writer;

public class IndentedJsonWriter extends JsonWriter {
    private int indentSize = 4;


    private int currentLevel;

    public IndentedJsonWriter() {
        super();
    }

    public IndentedJsonWriter(Writer writer) {
        super(writer);
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    private void carriageReturn(){
        stringBuilder.append("\n");
        for (int i = 0; i < getIndentSize()*getCurrentLevel(); i++){
            stringBuilder.append(' ');
        }
    }

    @Override
    public void writeObjectBegin() {
        super.writeObjectBegin();
        currentLevel++;
        carriageReturn();
    }

    @Override
    public void writeObjectEnd() {
        deleteLastSeparator();
        currentLevel--;
        carriageReturn();
        super.writeObjectEnd();
    }

    @Override
    public void writeArrayBegin() {
        carriageReturn();
        super.writeArrayBegin();
    }

    @Override
    public void writeArrayEnd() {
        super.writeArrayEnd();

    }

    @Override
    public void writeBoolean(boolean obj) {
        super.writeBoolean(obj);
    }

    @Override
    public void writeNull() {
        super.writeNull();
    }

    @Override
    public void writeNumber(Number number) {
        super.writeNumber(number);
    }

    @Override
    public void writePropertySeparator() {
        stringBuilder.append(' ');
        super.writePropertySeparator();
        stringBuilder.append(' ');
    }

    @Override
    public void writeString(String string) {
        super.writeString(string);
    }
}
