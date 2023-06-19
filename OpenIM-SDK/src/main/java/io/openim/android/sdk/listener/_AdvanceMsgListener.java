package io.openim.android.sdk.listener;

import java.util.List;

import io.openim.android.sdk.models.KeyValue;
import io.openim.android.sdk.models.Message;
import io.openim.android.sdk.models.ReadReceiptInfo;
import io.openim.android.sdk.models.RevokedInfo;
import io.openim.android.sdk.utils.CommonUtil;
import io.openim.android.sdk.utils.JsonUtil;


final public class _AdvanceMsgListener implements open_im_sdk_callback.OnAdvancedMsgListener {
    private final OnAdvanceMsgListener listener;

    public _AdvanceMsgListener(OnAdvanceMsgListener listener) {
        this.listener = listener;
    }

    @Override
    public void onMsgDeleted(String s) {
        if (listener != null) {
            Message msg = JsonUtil.toObj(s, Message.class);
            CommonUtil.runMainThread(() -> listener.onMsgDeleted(msg));
        }
    }

    @Override
    public void onNewRecvMessageRevoked(String s) {
        if (listener != null) {
            RevokedInfo info = JsonUtil.toObj(s, RevokedInfo.class);
            CommonUtil.runMainThread(() -> listener.onNewRecvMessageRevoked(info));
        }
    }

    @Override
    public void onRecvC2CReadReceipt(String s) {
        if (listener != null) {
            List<ReadReceiptInfo> list = JsonUtil.toArray(s, ReadReceiptInfo.class);
            CommonUtil.runMainThread(() -> listener.onRecvC2CReadReceipt(list));
        }
    }

    @Override
    public void onRecvGroupReadReceipt(String s) {
        if (listener != null) {
            List<ReadReceiptInfo> list = JsonUtil.toArray(s, ReadReceiptInfo.class);
            CommonUtil.runMainThread(() -> listener.onRecvGroupReadReceipt(list));
        }
    }

    @Override
    public void onRecvMessageExtensionsAdded(String s, String s1) {
        if (listener != null) {
            List<KeyValue> list = JsonUtil.toArray(s1, KeyValue.class);
            CommonUtil.runMainThread(() -> listener.onRecvMessageExtensionsAdded(s, list));
        }
    }

    @Override
    public void onRecvMessageExtensionsChanged(String s, String s1) {
        if (listener != null) {
            List<KeyValue> list = JsonUtil.toArray(s1, KeyValue.class);
            CommonUtil.runMainThread(() -> listener.onRecvMessageExtensionsChanged(s, list));
        }
    }

    @Override
    public void onRecvMessageExtensionsDeleted(String s, String s1) {
        if (listener != null) {
            List<String> list = JsonUtil.toArray(s1, String.class);
            CommonUtil.runMainThread(() -> listener.onRecvMessageExtensionsDeleted(s, list));
        }
    }


    @Override
    public void onRecvNewMessage(String s) {
        if (listener != null) {
            Message msg = JsonUtil.toObj(s, Message.class);
            CommonUtil.runMainThread(() -> listener.onRecvNewMessage(msg));
        }
    }

    @Override
    public void onRecvOfflineNewMessages(String s) {
        if (listener != null) {
            List<Message> list = JsonUtil.toArray(s, Message.class);
            CommonUtil.runMainThread(() -> listener.onRecvOfflineNewMessages(list));
        }
    }
}
