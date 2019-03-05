package de.chrisward.theyworkforyou.wrapper;

import android.support.annotation.IntDef;

public class Resource<T> {
    @IntDef({ERROR, SUCCESS, LOADING})
    public @interface Status {}
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int LOADING = 2;

    public final int status;
    public final T data;
    public final String message;

    public Resource(@Status int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
