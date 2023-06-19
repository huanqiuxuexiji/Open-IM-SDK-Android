package io.openim.android.sdk.manager;


import androidx.collection.ArrayMap;

import java.util.List;
import java.util.Map;

import io.openim.android.sdk.listener.BaseImpl;
import io.openim.android.sdk.listener.OnBase;
import io.openim.android.sdk.listener.OnGroupListener;
import io.openim.android.sdk.listener._GroupListener;
import io.openim.android.sdk.models.GroupApplicationInfo;
import io.openim.android.sdk.models.GroupInfo;
import io.openim.android.sdk.models.GroupInviteResult;
import io.openim.android.sdk.models.GroupMembersInfo;
import io.openim.android.sdk.utils.JsonUtil;
import io.openim.android.sdk.utils.ParamsUtil;
import open_im_sdk.Open_im_sdk;

/**
 * 群组管理器
 */
public class GroupManager {
    /**
     * 设置组监听器
     */
    public void setOnGroupListener(OnGroupListener listener) {
        Open_im_sdk.setGroupListener(new _GroupListener(listener));
    }

    /**
     * 邀请进群
     *
     * @param groupId 群组ID
     * @param userIDList 被邀请的用户id列表
     * @param reason  邀请说明
     * @param base    callback List<{@link GroupInviteResult}>>
     */
    public void inviteUserToGroup(OnBase<List<GroupInviteResult>> base, String groupId, List<String> userIDList, String reason) {
        Open_im_sdk.inviteUserToGroup(BaseImpl.arrayBase(base, GroupInviteResult.class), ParamsUtil.buildOperationID(), groupId, reason, JsonUtil.toString(userIDList));
    }

    /**
     * 踢出群
     *
     * @param groupID 群组ID
     * @param userIDList 被踢出群的用户id列表
     * @param reason  说明
     * @param base    callback List<{@link GroupInviteResult}>>
     */
    public void kickGroupMember(OnBase<List<GroupInviteResult>> base, String groupID, List<String> userIDList, String reason) {
        Open_im_sdk.kickGroupMember(BaseImpl.arrayBase(base, GroupInviteResult.class), ParamsUtil.buildOperationID(), groupID, reason, JsonUtil.toString(userIDList));
    }


    /**
     * 批量获取群成员信息
     *
     * @param groupID 群组ID
     * @param userIDList 群成员ID集合
     * @param base    callback List<{@link GroupMembersInfo}>
     **/
    public void getGroupMembersInfo(OnBase<List<GroupMembersInfo>> base, String groupID, List<String> userIDList) {
        Open_im_sdk.getSpecifiedGroupMembersInfo(BaseImpl.arrayBase(base, GroupMembersInfo.class), ParamsUtil.buildOperationID(), groupID, JsonUtil.toString(userIDList));
    }

    /**
     * 获取群成员
     *
     * @param groupID 群组ID
     * @param filter  过滤成员 1普通成员, 2群主，3管理员，0所有
     * @param offset  偏移量
     * @param count   每页数量
     */
    public void getGroupMemberList(OnBase<List<GroupMembersInfo>> base, String groupID, int filter, int offset, int count) {
        Open_im_sdk.getGroupMemberList(BaseImpl.arrayBase(base, GroupMembersInfo.class), ParamsUtil.buildOperationID(), groupID, filter, offset, count);
    }


    /**
     * 获取已加入的群列表
     *
     * @param base callback List<{@link GroupInfo}></>
     */
    public void getJoinedGroupList(OnBase<List<GroupInfo>> base) {
        Open_im_sdk.getJoinedGroupList(BaseImpl.arrayBase(base, GroupInfo.class), ParamsUtil.buildOperationID());
    }

    /**
     * 创建群
     */
    public void createGroup(OnBase<GroupInfo> base, GroupInfo groupInfo, List<String> memberUserIDs, List<String> adminUserIDs, String ownerUserID) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("groupInfo", groupInfo);
        map.put("memberUserIDs", memberUserIDs);
        map.put("adminUserIDs", adminUserIDs);
        map.put("ownerUserID", ownerUserID);
        Open_im_sdk.createGroup(BaseImpl.objectBase(base, GroupInfo.class), ParamsUtil.buildOperationID(), JsonUtil.toString(map));
    }

    /**
     * 设置或更新群资料
     *
     * @param groupID      群ID
     * @param groupName    群名称
     * @param faceURL      群icon
     * @param notification 群公告
     * @param introduction 群简介
     * @param ex           其他信息
     * @param base         callback String
     */
    public void setGroupInfo(OnBase<String> base, String groupID, String groupName, String faceURL, String notification, String introduction, String ex, int needVerification, int lookMemberInfo, int applyMemberFriend) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("groupID", groupID);
        map.put("groupName", groupName);
        map.put("notification", notification);
        map.put("introduction", introduction);
        map.put("faceURL", faceURL);
        map.put("ex", ex);
        map.put("needVerification", needVerification);
        map.put("lookMemberInfo", lookMemberInfo);
        map.put("applyMemberFriend", applyMemberFriend);
        Open_im_sdk.setGroupInfo(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), JsonUtil.toString(map));
    }


    /**
     * 批量获取群资料
     *
     * @param groupIDList 群ID集合
     * @param base    callback List<{@link GroupInfo}>
     */
    public void getGroupsInfo(OnBase<List<GroupInfo>> base, List<String> groupIDList) {
        Open_im_sdk.getSpecifiedGroupsInfo(BaseImpl.arrayBase(base, GroupInfo.class), ParamsUtil.buildOperationID(), JsonUtil.toString(groupIDList));
    }

    /**
     * 申请加入群组
     *
     * @param groupID        群组ID
     * @param reason     请求原因
     * @param joinSource 2：通过邀请  3：通过搜索  4：通过二维码
     * @param base       callback String
     */
    public void joinGroup(OnBase<String> base, String groupID, String reason, int joinSource) {
        Open_im_sdk.joinGroup(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, reason, joinSource);
    }

    /**
     * 退群
     *
     * @param groupID  群组ID
     * @param base callback String
     */
    public void quitGroup(OnBase<String> base, String groupID) {
        Open_im_sdk.quitGroup(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID);
    }

    /**
     * 转让群主
     *
     * @param groupID  群组ID
     * @param userID  新拥有者（群主）id
     * @param base callback String
     */
    public void transferGroupOwner(OnBase<String> base, String groupID, String userID) {
        Open_im_sdk.transferGroupOwner(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID);
    }

    /**
     * 收到群申请列表
     *
     * @param base callback {@link GroupApplicationInfo}
     */
    public void getGroupApplicationListAsRecipient(OnBase<List<GroupApplicationInfo>> base) {
        Open_im_sdk.getGroupApplicationListAsRecipient(BaseImpl.arrayBase(base, GroupApplicationInfo.class), ParamsUtil.buildOperationID());
    }

    /**
     * 发出群申请列表
     *
     * @param base callback {@link GroupApplicationInfo}
     */
    public void getGroupApplicationListAsApplicant(OnBase<List<GroupApplicationInfo>> base) {
        Open_im_sdk.getGroupApplicationListAsApplicant(BaseImpl.arrayBase(base, GroupApplicationInfo.class), ParamsUtil.buildOperationID());
    }

    /**
     * 接受入群申请
     *
     * @param groupID       群ID
     * @param userID       申请入群的用户ID
     * @param handleMsg 说明
     * @param base      callback String
     */
    public void acceptGroupApplication(OnBase<String> base, String groupID, String userID, String handleMsg) {
        Open_im_sdk.acceptGroupApplication(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID, handleMsg);

    }

    /**
     * 拒绝入群申请
     *
     * @param groupID       群ID
     * @param userID       申请入群的用户ID
     * @param handleMsg 说明
     * @param base      callback String
     */
    public void refuseGroupApplication(OnBase<String> base, String groupID, String userID, String handleMsg) {
        Open_im_sdk.refuseGroupApplication(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID, handleMsg);

    }

    /**
     * 解散群
     *
     * @param groupID 群ID
     */
    public void dismissGroup(OnBase<String> base, String groupID) {
        Open_im_sdk.dismissGroup(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID);
    }

    /**
     * 开启群禁言
     *
     * @param groupID  群ID
     * @param mute true开启
     */
    public void changeGroupMute(OnBase<String> base, String groupID, boolean mute) {
        Open_im_sdk.changeGroupMute(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, mute);

    }

    /**
     * 禁言群成员
     *
     * @param groupID     群ID
     * @param userID     群成员userID
     * @param seconds 禁言时间s
     */
    public void changeGroupMemberMute(OnBase<String> base, String groupID, String userID, long seconds) {
        Open_im_sdk.changeGroupMemberMute(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID, seconds);
    }

    /**
     * 修改所在群的昵称
     *
     * @param groupID           群ID
     * @param userID           群成员userID
     * @param groupNickname 群内显示名称
     */
    public void setGroupMemberNickname(OnBase<String> base, String groupID, String userID, String groupNickname) {
        Open_im_sdk.setGroupMemberNickname(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID, groupNickname);
    }

    /**
     * 根据关键词搜索群组
     *
     * @param keywordList       关键词
     * @param isSearchGroupID   是通过群组id进行查询
     * @param isSearchGroupName 是通过群名称查询
     */
    public void searchGroups(OnBase<List<GroupInfo>> base, List<String> keywordList, boolean isSearchGroupID, boolean isSearchGroupName) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("keywordList", keywordList);
        map.put("isSearchGroupID", isSearchGroupID);
        map.put("isSearchGroupName", isSearchGroupName);
        Open_im_sdk.searchGroups(BaseImpl.arrayBase(base, GroupInfo.class), ParamsUtil.buildOperationID(), JsonUtil.toString(map));
    }

    /**
     * 设置群管理员
     *
     * @param groupID   组ID号
     * @param userID    用户ID号
     * @param roleLevel 角色 {@link io.openim.android.sdk.enums.GroupRole}
     */
    public void setGroupMemberRoleLevel(OnBase<String> base, String groupID, String userID, long roleLevel) {
        Open_im_sdk.setGroupMemberRoleLevel(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, userID, roleLevel);
    }

    /**
     * 根据加入时间分页获取组成员列表
     *
     * @param groupID           组ID号
     * @param offset            开始下标
     * @param count             每页大小
     * @param joinTimeBegin     加入开始时间
     * @param joinTimeEnd       加入结束时间
     * @param excludeUserIDList 排除的用户
     */
    public void getGroupMemberListByJoinTime(OnBase<List<GroupMembersInfo>> base, String groupID, int offset, int count, long joinTimeBegin, long joinTimeEnd, List<String> excludeUserIDList) {
        Open_im_sdk.getGroupMemberListByJoinTimeFilter(BaseImpl.arrayBase(base, GroupMembersInfo.class), ParamsUtil.buildOperationID(), groupID, offset, count, joinTimeBegin, joinTimeEnd, JsonUtil.toString(excludeUserIDList));
    }

    /**
     * 设置进群验证
     *
     * @param groupID          组ID号
     * @param needVerification {@link io.openim.android.sdk.enums.GroupVerification}
     */
    public void setGroupVerification(OnBase<String> base, String groupID, int needVerification) {
        Open_im_sdk.setGroupVerification(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, needVerification);
    }

    /**
     * 不允许通过群获取成员资料
     *
     * @param groupID 组ID号
     * @param status  0：关闭，1：打开
     */
    public void setGroupLookMemberInfo(OnBase<String> base, String groupID, int status) {
        Open_im_sdk.setGroupLookMemberInfo(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, status);
    }

    /**
     * 不允许通过群添加好友
     *
     * @param groupID 组ID号
     * @param status  0：关闭，1：打开
     */
    public void setGroupApplyMemberFriend(OnBase<String> base, String groupID, int status) {
        Open_im_sdk.setGroupApplyMemberFriend(BaseImpl.stringBase(base), ParamsUtil.buildOperationID(), groupID, status);
    }

    /**
     * 获取群拥有者，管理员
     *
     * @param groupID 组ID号
     */
    public void getGroupMemberOwnerAndAdmin(OnBase<List<GroupMembersInfo>> base, String groupID) {
        Open_im_sdk.getGroupMemberOwnerAndAdmin(BaseImpl.arrayBase(base, GroupMembersInfo.class), ParamsUtil.buildOperationID(), groupID);
    }

    /**
     * 根据关键词搜索群组
     *
     * @param groupID                群id
     * @param keywordList            关键词
     * @param isSearchUserID         是否以关键词搜成员id
     * @param isSearchMemberNickname 是否以关键词搜索成员昵称
     * @param offset                 开始index
     * @param count                  每次获取的总数
     */
    public void searchGroupMembers(OnBase<List<GroupMembersInfo>> base, String groupID, List<String> keywordList, boolean isSearchUserID, boolean isSearchMemberNickname, int offset, int count) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("groupID", groupID);
        map.put("keywordList", keywordList);
        map.put("isSearchUserID", isSearchUserID);
        map.put("isSearchMemberNickname", isSearchMemberNickname);
        map.put("offset", offset);
        map.put("count", count);
        Open_im_sdk.searchGroupMembers(BaseImpl.arrayBase(base, GroupMembersInfo.class), ParamsUtil.buildOperationID(), JsonUtil.toString(map));
    }
}
