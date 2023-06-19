package io.openim.android.sdk;

import androidx.collection.ArrayMap;

import java.util.Map;

import io.openim.android.sdk.internal.log.LogcatHelper;
import io.openim.android.sdk.listener.BaseImpl;
import io.openim.android.sdk.listener.OnBase;
import io.openim.android.sdk.listener.OnConnListener;
import io.openim.android.sdk.listener.OnListenerForService;
import io.openim.android.sdk.listener.OnPutFileListener;
import io.openim.android.sdk.listener._ConnListener;
import io.openim.android.sdk.listener._ListenerForService;
import io.openim.android.sdk.listener._PutFileListener;
import io.openim.android.sdk.manager.ConversationManager;
import io.openim.android.sdk.manager.FriendshipManager;
import io.openim.android.sdk.manager.GroupManager;
import io.openim.android.sdk.manager.MessageManager;
import io.openim.android.sdk.manager.SignalingManager;
import io.openim.android.sdk.manager.UserInfoManager;
import io.openim.android.sdk.utils.JsonUtil;
import io.openim.android.sdk.utils.ParamsUtil;
import open_im_sdk.Open_im_sdk;

public class OpenIMClient {
    //    public ImManager imManager;
    public ConversationManager conversationManager;
    public FriendshipManager friendshipManager;
    public GroupManager groupManager;
    public MessageManager messageManager;
    public UserInfoManager userInfoManager;
    public SignalingManager signalingManager;
//    public WorkMomentsManager workMomentsManager;
//    public OrganizationManager organizationManager;

    private OpenIMClient() {
//        imManager = new ImManager();
        conversationManager = new ConversationManager();
        friendshipManager = new FriendshipManager();
        groupManager = new GroupManager();
        messageManager = new MessageManager();
        userInfoManager = new UserInfoManager();
        signalingManager = new SignalingManager();
//        workMomentsManager = new WorkMomentsManager();
//        organizationManager = new OrganizationManager();
    }

    private static class Singleton {
        private static final OpenIMClient INSTANCE = new OpenIMClient();
    }

    public static OpenIMClient getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 初始化sdk
     * 注：在创建图片，语音，视频，文件等需要路径参数的消息体时，
     * 如果选择的是非全路径方法如：createSoundMessage（全路径方法为：createSoundMessageFromFullPath）,
     * 需要将文件自行拷贝到dbPath目录下，如果此时文件路径为 apath+"/sound/a.mp3"，则参数path的值为：/sound/a.mp3。
     * 如果选择的全路径方法，路径为你文件的实际路径不需要再拷贝。
     *
     * @param platform             平台{@link io.openim.android.sdk.enums.Platform}
     * @param apiAddr              SDK的API接口地址。如：http:xxx:10000
     * @param wsAddr               SDK的web socket地址。如： ws:xxx:17778
     * @param dataDir              数据存储目录路径
     * @param logLevel             日志等级，如：6
     * @param objectStorage        图片上传配置 如：cos
     * @param encryptionKey        加密key
     * @param listener             SDK初始化监听
     * @param isNeedEncryption     启用压缩
     * @param isCompression        启用加密
     * @param isExternalExtensions 消息扩展
     * @return boolean   true成功; false失败
     */
    public boolean initSDK(int platform, String apiAddr, String wsAddr, String dataDir, int logLevel, String objectStorage, String encryptionKey, boolean isNeedEncryption, boolean isCompression, boolean isExternalExtensions, boolean isLogStandardOutput, String logFilePath, OnConnListener listener) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("platform", platform);
        map.put("apiAddr", apiAddr);
        map.put("wsAddr", wsAddr);
        map.put("dataDir", dataDir);
        map.put("logLevel", logLevel);
        map.put("objectStorage", objectStorage);
        map.put("encryptionKey", encryptionKey);
        map.put("isNeedEncryption", isNeedEncryption);
        map.put("isCompression", isCompression);
        map.put("isExternalExtensions", isExternalExtensions);
        map.put("isLogStandardOutput", isLogStandardOutput);
        map.put("logFilePath", logFilePath);
        boolean initialized = Open_im_sdk.initSDK(new _ConnListener(listener), ParamsUtil.buildOperationID(), JsonUtil.toString(map));
        LogcatHelper.logDInDebug(String.format("Initialization successful: %s", initialized));
        return initialized;
    }

//    /**
//     * 反初始化sdk
//     */
//    public void unInitSDK() {
//        Open_im_sdk.unInitSDK();
//    }

    /**
     * 登录
     *
     * @param userID   用户id
     * @param token 用户token
     * @param base  callback String
     */
    public void login(OnBase<String> base, String userID, String token) {
        Open_im_sdk.login(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), userID, token);
    }


    /**
     * 登出
     */
    public void logout(OnBase<String> base) {
        Open_im_sdk.logout(BaseImpl.stringBase(base), ParamsUtil.buildOperationID());
    }

    /**
     * 查询登录状态
     */
//    public int getLoginStatus() {
//        return Open_im_sdk.getLoginStatus();
//    }


//    public void wakeUp(OnBase<String> base) {
//        Open_im_sdk.wakeUp(BaseImpl.stringBase(base), ParamsUtil.buildOperationID());
//    }


    /**
     * 上传文件到服务器
     *
     * @param path 路径
     */
    public void putFile(OnBase<String> base, OnPutFileListener listener, String putID, String path, String name) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("putID", putID);
        map.put("filePath", path);
        map.put("name", name);
        Open_im_sdk.putFile(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), JsonUtil.toString(map), new _PutFileListener(listener));
    }

    /**
     * 更新firebase token
     *
     * @param fcmToken token
     */
    public void updateFcmToken(OnBase<String> base, String fcmToken) {
        Open_im_sdk.updateFcmToken(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), fcmToken);
    }

    /**
     * 标记app处于后台
     * 可以用于后台不接收ws消息，走离线推送
     */
    public void setAppBackgroundStatus(OnBase<String> base, boolean isBackground) {
        Open_im_sdk.setAppBackgroundStatus(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), isBackground);
    }

    public void networkStatusChanged(OnBase<String> base) {
        Open_im_sdk.networkStatusChanged(BaseImpl.stringBase(base), ParamsUtil.buildOperationID());
    }


    public void setOnListenerForService(OnListenerForService listener) {
        Open_im_sdk.setListenerForService(new _ListenerForService(listener));
    }

//    public void networkChanged(OnBase<String> base) {
//        Open_im_sdk.networkChanged(BaseImpl.stringBase(base), ParamsUtil.buildOperationID());
//    }
}

