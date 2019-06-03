package com.github.remcalmels;

import com.github.remcalmels.config.TypeProperties;
import com.github.remcalmels.exception.SheepException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * Sheep objet factory. A sheep is a valued object
 */
@Component
@Slf4j
public class SheepFactory {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TypeProperties typeProperties;

    /**
     * Build a "sheep" (valued object) from the class specified in parameters
     *
     * The factory handles the following types :
     * String : valued with the class attribute name
     * Boolean : valued from properties file
     * Integer : valued from properties file
     * Long : valued from properties file
     *
     * @param source class
     * @return an optional which contains a class instance, valued with default values
     * @throws SheepException if an error occured during the build
     */
    public Optional build(Class source) throws SheepException {
        Object sheep = null;
        if (Objects.nonNull(source)) {
            try {
                sheep = source.getDeclaredConstructor().newInstance();
                for (Field f : source.getDeclaredFields()) {
                    if (!Modifier.isFinal(f.getModifiers())) {  // final attributes are not processed
                        boolean accessible = f.isAccessible();
                        f.setAccessible(Boolean.TRUE);
                        this.setValue(sheep, f);                // set the value
                        f.setAccessible(accessible);
                    }
                }
                log.info(messageSource.getMessage("sheep.build.success", new String[] {source.getSimpleName()}, Locale.getDefault()));
            } catch (Exception e) {
                String message = messageSource.getMessage("sheep.build.error", new String[] {source.getSimpleName()}, Locale.getDefault());
                log.error(message);
                throw new SheepException(message, e);
            }
        }
        return Optional.ofNullable(sheep);
    }

    private void setValue(Object sheep, Field f) throws IllegalAccessException {
        if (String.class.isAssignableFrom(f.getType()) || CharSequence.class.isAssignableFrom(f.getType())) {
            f.set(sheep, f.getName());
        } else {
            f.set(sheep, typeProperties.getValue(f.getType()));
        }
    }

}
