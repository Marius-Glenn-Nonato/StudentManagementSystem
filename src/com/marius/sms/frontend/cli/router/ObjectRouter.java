package com.marius.sms.frontend.cli.router;

public interface ObjectRouter<T> {
    boolean routeToView(int choice, T object);
}
