package ru.grigorev.epam.homework.JavaSE2.task7;

import java.lang.annotation.*;

/**
 * @author Dmitriy Grigorev
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Tannotation {
}
