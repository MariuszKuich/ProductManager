package pl.mariuszk.productmanager.factory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.exception.FieldTypeNotSupportedException;

@Service
public class PropertyTypeFactory {

    public Object getObjectOfType(FieldType fieldType) throws FieldTypeNotSupportedException {
        switch (fieldType) {
            case STRING:
                return StringUtils.EMPTY;
            case LONG:
                return 0L;
            case DOUBLE:
                return 0.0;
            default:
                throw new FieldTypeNotSupportedException();
        }
    }
}
