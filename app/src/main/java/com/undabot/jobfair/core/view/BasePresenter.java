package com.undabot.jobfair.core.view;

public interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}
