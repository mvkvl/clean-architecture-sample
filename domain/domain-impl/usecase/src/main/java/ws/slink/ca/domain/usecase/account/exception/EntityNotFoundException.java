package ws.slink.ca.domain.usecase.account.exception;

import ws.slink.ca.domain.usecase.common.exception.UseCaseException;

public class EntityNotFoundException extends UseCaseException {

    private static final String MESSAGE = "%s not found";

    public EntityNotFoundException(String entityNameOrId) {
        super(String.format(MESSAGE, entityNameOrId));
    }

}
