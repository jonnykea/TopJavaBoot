package ru.javaops.topjava.util.validation;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.HasId;
import ru.javaops.topjava.error.DataConflictException;
import ru.javaops.topjava.error.IllegalRequestDataException;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkOwner(int restaurantId, int restaurantIdDishForUpdate) {
        if (restaurantId != restaurantIdDishForUpdate) {
            throw new DataConflictException("entity with " + restaurantId + " belong to other restaurant");
        }
    }
}