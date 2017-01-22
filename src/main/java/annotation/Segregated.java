package annotation;

import javax.persistence.Entity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Entity
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Segregated {
    String tableName();
}
