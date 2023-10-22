package ws.slink.ca.api.exception.account;

import ws.slink.ca.api.exception.UseCaseException;

public class EntityNotFoundException extends UseCaseException {

    private static final String MESSAGE = "%s not found";

    public EntityNotFoundException(String entityNameOrId) {
        super(String.format(MESSAGE, entityNameOrId));
    }

}
