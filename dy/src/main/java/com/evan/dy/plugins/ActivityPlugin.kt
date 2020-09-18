package com.evan.dy.plugins

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.ehook.plugins.interfaces.IActivityHook
import com.ehook.utils.LogUtil
import com.ehook.utils.RouterUtil
import com.evan.dy.api.ChatRoomApi
import com.evan.dy.floatingview.FloatingView
import kotlin.jvm.internal.Intrinsics


object ActivityPlugin : IActivityHook {

    /*  ------------------  IActivityHook  ----------------- */
    override fun onCreate(activity: Activity, savedInstanceState: Bundle?) {
        LogUtil.e(
            ActivityPlugin.javaClass.simpleName,
            "onCreate  ${activity.javaClass.simpleName}"
        )
        if (activity.javaClass.simpleName.contains("ExternalWechatUserMessageListActivity")) {
//            Debug.hook()
        }
        FloatingView.get().add(activity)
            .text("开始").setTextClick {
                Toast.makeText(activity, "1", 1).show()
                if (FloatingView.get().isStart) {
                    FloatingView.get().text("停止")
                } else {
                    FloatingView.get().text("开始")
                }
            }.text1("设置").setText1Click {
                RouterUtil.startActivity(activity,"com.ehook.dy","com.ehook.dy.MainActivity")
                Toast.makeText(activity, "2", 1).show()
            }
    }

    override fun onResume(activity: Activity) {
        LogUtil.e(
            ActivityPlugin.javaClass.simpleName,
            "onResume  ${activity.javaClass.simpleName}"
        )
//        ChatRoomApi.setCommentWidget(activity)
        FloatingView.get().attach(activity)

    }

    override fun onPause(activity: Activity) {
        FloatingView.get().detach(activity)
        LogUtil.e(
            ActivityPlugin.javaClass.simpleName,
            "onPause  ${activity.javaClass.simpleName}"
        )
//        if (Intrinsics.areEqual(ChatRoomApi.getCommentWidget(), activity)) {
//            ChatRoomApi.setCommentWidget(null)
//        }
    }
}
