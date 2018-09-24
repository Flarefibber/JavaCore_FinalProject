package JSONSerializer.Tests;

import JSONSerializer.Serializer.JsonSerializer;
import JSONSerializer.Writer.IndentedJsonWriter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

class JsonSerializerTest {
//    JsonSerializer serializer = JsonSerializer.getInstance();
//
//    @Test
//    void isIndent() {
//        serializer.isIndent();
//    }
//
//    @Test
//    void setIndent() {
//        serializer.setIndent(true);
//    }
//
//    @Test
//    void serialize() {
//        serializer.serialize(420);
//    }
//
//    @Test
//    void serialize1() {
//        try {
//            serializer.serialize(97, new FileOutputStream());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void serialize2() {
//        try {
//            serializer.serialize(464, new FileOutputStream(), Charset.forName("UTF-8"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void serialize3() {
//        try {
//            serializer.serialize(44, new FileWriter());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void serialize4() {
//        try {
//            serializer.serialize(150, new IndentedJsonWriter(new FileWriter()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void getMapper() {
//        serializer.getMapper(Integer.class);
//    }

}