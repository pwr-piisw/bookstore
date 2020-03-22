package io.github.pwrpiiws.bookstore.app.store;

import java.util.UUID;

import lombok.Getter;

@Getter
public enum BookIDs {

    FIASCO, UBIK, A2001;

    private UUID id = UUID.randomUUID();
}
