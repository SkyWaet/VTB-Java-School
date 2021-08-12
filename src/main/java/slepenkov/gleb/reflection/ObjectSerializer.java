package slepenkov.gleb.reflection;

import slepenkov.gleb.shopandproducts.products.Book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ObjectSerializer {
    private int x = 0;
    public double y = 1;
    protected Book book = new Book("aaaa", 1, 1.0);

    private static boolean isSerializable(Class obj) {
        if (obj != null) {
            List<Class> interfaces = new ArrayList<>();
            interfaces.addAll(Arrays.asList(obj.getInterfaces()));
            return interfaces.contains(java.io.Serializable.class) ||
                    isSerializable(obj.getSuperclass());
        }
        return false;
    }

    private static List<Field> getFields(Class<?> clazz) {
        List<Field> result = new ArrayList<>();
        if (clazz != null) {
            result.addAll(Arrays.asList(clazz.getDeclaredFields()));
            result.addAll(getFields(clazz.getSuperclass()));
        }
        //System.out.println(result);
        return result;
    }


    public static void serialize(Object object, ObjectOutputStream stream) throws IOException, IllegalAccessException {
        if (Objects.isNull(object) || isSerializable(object.getClass())) {
            stream.writeObject(object);
        } else {
            Field[] fields = getFields(object.getClass()).toArray(Field[]::new);
            System.out.println(Arrays.toString(fields));
            for (var field : fields) {
                field.setAccessible(true);
                String fieldTypeName = field.getType().getSimpleName().toLowerCase();
                switch (fieldTypeName) {
                    case "byte":
                        stream.writeByte((Byte) field.get(object));
                        break;
                    case "short":
                        stream.writeShort((Short) field.get(object));
                        break;
                    case "int":
                    case "integer":
                        stream.writeInt((Integer) field.get(object));
                        break;
                    case "long":
                        stream.writeLong((Long) field.get(object));
                        break;
                    case "float":
                        stream.writeFloat((Float) field.get(object));
                        break;
                    case "double":
                        stream.writeDouble((Double) field.get(object));
                        break;
                    case "boolean":
                        stream.writeBoolean((Boolean) field.get(object));
                        break;
                    case "char":
                        stream.writeChar((Character) field.get(object));
                        break;
                    default:
                        serialize(field.get(object), stream);
                        break;
                }
            }
        }
    }

    public static Object deserialize(Class<?> clazz, ObjectInputStream stream) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (isSerializable(clazz)) {
            return stream.readObject();
        } else {
            Object result = clazz.getConstructor().newInstance();
            Field[] fields = getFields(clazz).toArray(Field[]::new);
            for (var field : fields) {
                field.setAccessible(true);
                String fieldTypeName = field.getType().getSimpleName().toLowerCase();
                switch (fieldTypeName) {
                    case "byte":
                        field.set(result, stream.readByte());
                        break;
                    case "short":
                        field.set(result, stream.readShort());
                        break;
                    case "int":
                    case "integer":
                        field.set(result, stream.readInt());
                        break;
                    case "long":
                        field.set(result, stream.readLong());
                        break;
                    case "float":
                        field.set(result, stream.readFloat());
                        break;
                    case "double":
                        field.set(result, stream.readDouble());
                        break;
                    case "boolean":
                        field.set(result, stream.readBoolean());
                        break;
                    case "char":
                        field.set(result, stream.readChar());
                        break;
                    default:
                        field.set(result, deserialize(field.getType(), stream));
                        break;
                }
            }

            return result;

        }

    }

}
