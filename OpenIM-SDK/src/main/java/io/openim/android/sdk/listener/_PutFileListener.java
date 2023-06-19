package io.openim.android.sdk.listener;

import io.openim.android.sdk.utils.CommonUtil;

final public class _PutFileListener implements open_im_sdk_callback.PutFileCallback {
    private final OnPutFileListener listener;

    public _PutFileListener(OnPutFileListener listener) {
        this.listener = listener;
    }

    @Override
    public void hashComplete(String s, long l) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.hashComplete(s, l));
        }
    }

    @Override
    public void hashProgress(long l, long l1) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.hashProgress(l, l1));
        }
    }

    @Override
    public void open(long l) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.open(l));
        }
    }

    @Override
    public void putComplete(long l, long l1) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.putComplete(l, l1));
        }
    }

    @Override
    public void putProgress(long l, long l1, long l2) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.putProgress(l, l1, l2));
        }
    }

    @Override
    public void putStart(long l, long l1) {
        if (null != listener) {
            CommonUtil.runMainThread(() -> listener.putStart(l, l1));
        }
    }
}
