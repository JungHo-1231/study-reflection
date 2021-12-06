package part1.di;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        // 인스턴스 생성
        T instance = createInstance(classType);

        // 해당 클래스에 필드를 전부 꺼낸다.
        Field[] declaredFields = classType.getDeclaredFields();

        Arrays.stream(declaredFields).forEach(
                // 필드 값을 반복문을 돌면서
                field ->{
                    // 필드에 Inject.class 의 어노테이션이 붙어 있는지 확인한다.
                    if (field.getAnnotation(Inject.class) != null) {
                        // 있다면 get type 을 가져온다.
                        // 여기서는 BookService 에 Inject 어노테이션이 붙어 있는 반환타입은
                        // BookRepository 가 된다.
                        Class<?> type = field.getType();

                        // BookService 인스턴스를 생성할 때와 마찬가지로 BookRepository 를 생성해준 다음
                        Object fieldInstance = createInstance(type);
                        // private 로 설정되어 있는 경우를 대비하여 ture 로 변경해준다.
                        field.setAccessible(true);
                        try {
                            // BookService 에 필드에 BookRepository 의 인스턴스를 주입해준다.
                            field.set(instance, fieldInstance);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );



        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
