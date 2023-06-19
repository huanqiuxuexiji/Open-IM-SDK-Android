package io.openim.android.sdk.listener;


public interface OnPutFileListener extends OnBase<String> {
    void hashComplete(String hash, long total);

    void hashProgress(long current, long total);

    void open(long size);

    void putComplete(long total, long putType);

    void putProgress(long save, long current, long total);

    void putStart(long current, long total);
}
