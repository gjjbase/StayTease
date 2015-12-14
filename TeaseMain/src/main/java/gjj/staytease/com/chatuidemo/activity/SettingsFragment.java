/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gjj.staytease.com.chatuidemo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;

import java.util.ArrayList;
import java.util.List;

import gjj.staytease.com.R;
import gjj.staytease.com.applib.controller.HXSDKHelper;
import gjj.staytease.com.chatuidemo.Constant;
import gjj.staytease.com.chatuidemo.DemoHXSDKHelper;
import gjj.staytease.com.chatuidemo.DemoHXSDKModel;

/**
 * 设置界面
 *
 * @author Administrator
 */
public class SettingsFragment extends Fragment implements OnClickListener {

    /**
     * 设置声音布局
     */
    private LinearLayout rl_switch_sound;
    /**
     * 设置震动布局
     */
    private LinearLayout rl_switch_vibrate;
    /**
     * 设置扬声器布局
     */
    private LinearLayout rl_switch_speaker;

    /**
     * 新消息通知RadioButton
     */
    private LinearLayout rad_notification;


    private LinearLayout blacklistContainer;

    private LinearLayout userProfileContainer;

    private LinearLayout rl_switch_chatroom_leave;

    /**
     * 退出按钮
     */
    private Button logoutBtn;

    private EMChatOptions chatOptions;

    /**
     * 诊断
     */
    private LinearLayout llDiagnose;
    /**
     * iOS离线推送昵称
     */
    private LinearLayout pushNick;

    DemoHXSDKModel model;

    private List<SwitchCompat> switchCompats;
    private List<LinearLayout> linearLayoutList;
    private List<String> stringList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversation_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        switchCompats = new ArrayList<>();
        linearLayoutList = new ArrayList<>();
        stringList = new ArrayList<>();
        rl_switch_sound = (LinearLayout) getView().findViewById(R.id.rl_switch_sound);
        rl_switch_vibrate = (LinearLayout) getView().findViewById(R.id.rl_switch_vibrate);
        rad_notification = (LinearLayout) getView().findViewById(R.id.rad_notification);
        rl_switch_speaker = (LinearLayout) getView().findViewById(R.id.rl_switch_speaker);
        rl_switch_chatroom_leave = (LinearLayout) getView().findViewById(R.id.rl_switch_chatroom_owner_leave);

        linearLayoutList.add(rad_notification);
        linearLayoutList.add(rl_switch_sound);
        linearLayoutList.add(rl_switch_vibrate);
        linearLayoutList.add(rl_switch_speaker);
        linearLayoutList.add(rl_switch_chatroom_leave);

        stringList.add(getString(R.string.newnotify));
        stringList.add(getString(R.string.voice));
        stringList.add(getString(R.string.shake));
        stringList.add(getString(R.string.yangshengqi));
        stringList.add(getString(R.string.chatroom_allow_owner_leave));


        int lenth = linearLayoutList.size();
        for (int i = 0; i < lenth; i++) {
            ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.AppTheme);
            SwitchCompat sc = new SwitchCompat(ctw);
            sc.setText(stringList.get(i));
            linearLayoutList.get(i).addView(sc);
            switchCompats.add(sc);
        }
        logoutBtn = (Button) getView().findViewById(R.id.btn_logout);
        if (!TextUtils.isEmpty(EMChatManager.getInstance().getCurrentUser())) {
            logoutBtn.setText(getString(R.string.button_logout) + "(" + EMChatManager.getInstance().getCurrentUser() + ")");
        }


        blacklistContainer = (LinearLayout) getView().findViewById(R.id.ll_black_list);
        userProfileContainer = (LinearLayout) getView().findViewById(R.id.ll_user_profile);
        llDiagnose = (LinearLayout) getView().findViewById(R.id.ll_diagnose);
        pushNick = (LinearLayout) getView().findViewById(R.id.ll_set_push_nick);

        blacklistContainer.setOnClickListener(this);
        userProfileContainer.setOnClickListener(this);
        rl_switch_sound.setOnClickListener(this);
        rl_switch_vibrate.setOnClickListener(this);
        rl_switch_speaker.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        llDiagnose.setOnClickListener(this);
        pushNick.setOnClickListener(this);
        rl_switch_chatroom_leave.setOnClickListener(this);

        chatOptions = EMChatManager.getInstance().getChatOptions();

        model = (DemoHXSDKModel) HXSDKHelper.getInstance().getModel();

        // 震动和声音总开关，来消息时，是否允许此开关打开
        // the vibrate and sound notification are allowed or not?
        if (model.getSettingMsgNotification()) {
            switchCompats.get(0).setChecked(true);
        } else {
            switchCompats.get(0).setChecked(false);
        }
        switchCompats.get(0).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        for (int i = 0; i < switchCompats.size(); i++) {
            final int k = i;
            switchCompats.get(i).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switch (k) {
                        case 0:
                            if (isChecked) {
                                chatOptions.setNotificationEnable(false);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                            } else {
                                switchCompats.get(1).setChecked(false);
                                switchCompats.get(2).setChecked(false);
                                chatOptions.setNotificationEnable(true);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgNotification(true);
                            }
                            break;
                        case 1:
                            if (isChecked) {
                                chatOptions.setNoticeBySound(false);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgSound(false);
                            } else {
                                chatOptions.setNoticeBySound(true);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgSound(true);
                            }
                            break;
                        case 2:
                            if (isChecked) {
                                chatOptions.setNoticedByVibrate(false);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(false);
                            } else {
                                chatOptions.setNoticedByVibrate(true);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
                            }
                            break;
                        case 3:
                            if (isChecked) {
                                chatOptions.setUseSpeaker(false);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgSpeaker(false);
                            } else {
                                chatOptions.setUseSpeaker(true);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
                            }
                            break;
                        case 4:
                            if (isChecked) {
                                chatOptions.allowChatroomOwnerLeave(false);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                model.allowChatroomOwnerLeave(false);
                            } else {
                                chatOptions.allowChatroomOwnerLeave(true);
                                EMChatManager.getInstance().setChatOptions(chatOptions);
                                model.allowChatroomOwnerLeave(true);
                            }
                            break;
                    }
                }
            });
        }
        // 是否打开声音
        // sound notification is switched on or not?
        if (model.getSettingMsgSound()) {
            switchCompats.get(1).setChecked(true);
        } else {
            switchCompats.get(1).setChecked(false);
        }
        // 是否打开震动
        // vibrate notification is switched on or not?
        if (model.getSettingMsgVibrate()) {
            switchCompats.get(2).setChecked(true);

        } else {
            switchCompats.get(2).setChecked(false);

        }
        // 是否打开扬声器
        // the speaker is switched on or not?
        if (model.getSettingMsgSpeaker()) {
            switchCompats.get(3).setChecked(true);
        } else {
            switchCompats.get(3).setChecked(false);
        }

        // 是否允许聊天室owner leave
        if (model.isChatroomOwnerLeaveAllowed()) {
            switchCompats.get(4).setChecked(true);

        } else {
            switchCompats.get(4).setChecked(false);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout: //退出登陆
                logout();
                break;
            case R.id.ll_black_list:
                startActivity(new Intent(getActivity(), BlacklistActivity.class));
                break;
            case R.id.ll_diagnose:
                startActivity(new Intent(getActivity(), DiagnoseActivity.class));
                break;
            case R.id.ll_set_push_nick:
                startActivity(new Intent(getActivity(), OfflinePushNickActivity.class));
                break;
            case R.id.ll_user_profile:
                startActivity(new Intent(getActivity(), UserProfileActivity.class).putExtra("setting", true));
                break;
            default:
                break;
        }

    }

    void logout() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        DemoHXSDKHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        // 重新显示登陆页面
                        ((MainActivity) getActivity()).finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                        Toast.makeText(getActivity(), "unbind devicetokens failed", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((MainActivity) getActivity()).isConflict) {
            outState.putBoolean("isConflict", true);
        } else if (((MainActivity) getActivity()).getCurrentAccountRemoved()) {
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

}
