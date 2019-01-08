package com.lovely.pig.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

	private NotificationManager mNotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取通知管理器对象
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	// 标准视图(Normal view)
	public void showOne(View view) {
		Bitmap btm = BitmapFactory.decodeResource(getResources(),
				R.mipmap.avatar);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MainActivity.this).setSmallIcon(R.mipmap.push)
				.setContentTitle("5 new message")
				.setContentText("twain@android.com");
		mBuilder.setTicker("New message");//第一次提示消息的时候显示在通知栏上
		mBuilder.setNumber(12);
		mBuilder.setLargeIcon(btm);
		mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("AAAAAAAAAAAAAAAAAAAAAAAAA"));
		mBuilder.setAutoCancel(true);//自己维护通知的消失


		//构建一个Intent
		Intent resultIntent = new Intent(MainActivity.this,
				ResultActivity.class);
		//封装一个Intent
		PendingIntent resultPendingIntent = PendingIntent.getActivity(
				MainActivity.this, 0, resultIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
//		mBuilder.addAction(R.mipmap.push, "开启前", resultPendingIntent);
		//	FLAG_CANCEL_CURRENT：如果构建的PendingIntent已经存在，则取消前一个，重新构建一个。
		//	FLAG_NO_CREATE：如果前一个PendingIntent已经不存在了，将不再构建它。
		//	FLAG_ONE_SHOT：表明这里构建的PendingIntent只能使用一次。
		//	FLAG_UPDATE_CURRENT：如果构建的PendingIntent已经存在，则替换它，常用。

		// 设置通知主题的意图
		mBuilder.setContentIntent(resultPendingIntent);
		mBuilder.setFullScreenIntent(resultPendingIntent, true);
		mNotificationManager.notify(1, mBuilder.build());
	}

	public void cancelOne(View view) {
		mNotificationManager.cancel(1);
	}

	public void showTwo(View view) {
		Bitmap btm = BitmapFactory.decodeResource(getResources(),
				R.mipmap.avatar);
		//构建一个Intent
		Intent resultIntent = new Intent(MainActivity.this,
				ResultActivity.class);
		//封装一个Intent
		PendingIntent resultPendingIntent = PendingIntent.getActivity(
				MainActivity.this, 0, resultIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				MainActivity.this)
				.setSmallIcon(R.mipmap.push)
				.setLargeIcon(btm)
				.setNumber(13)
				.setAutoCancel(true)
				.setContentIntent(resultPendingIntent)
				.setStyle(new NotificationCompat.InboxStyle()
						.addLine(
								"M.Twain (Google+) Haiku is more than a cert...")
						.addLine("M.Twain Reminder")
						.addLine("M.Twain Lunch?")
						.addLine("M.Twain Revised Specs")
						.addLine("M.Twain ")
						.addLine(
								"Google Play Celebrate 25 billion apps with Goo..")
						.addLine(
								"Stack Exchange StackOverflow weekly Newsl...")
						.setBigContentTitle("6 new message")
						.setSummaryText("mtwain@android.com"));
		builder.setFullScreenIntent(resultPendingIntent, true);
		builder.setVisibility(Notification.VISIBILITY_PUBLIC);
		mNotificationManager.notify(2, builder.build());
	}

	public void cancelTwo(View view) {
		mNotificationManager.cancel(2);
	}

	public void showThree(View view) {
		final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
				.setSmallIcon(R.mipmap.push)
				.setContentTitle("Picture Download")
				.setContentText("Download in progress");
		builder.setAutoCancel(true);
		//通过一个子线程，动态增加进度条刻度
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				for (incr = 0; incr <= 100; incr += 5) {
					builder.setProgress(100, incr, false);
					mNotificationManager.notify(3, builder.build());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
					}
				}
				builder.setContentText("Download complete")
						.setProgress(0, 0, false);
				mNotificationManager.notify(3, builder.build());
			}
		}).start();
	}

	public void showFour(View view) {
		RemoteViews contentViews = new RemoteViews(getPackageName(),
				R.layout.custom_notification);
		//通过控件的Id设置属性
		contentViews
				.setImageViewResource(R.id.imageNo, R.mipmap.avatar);
		contentViews.setTextViewText(R.id.titleNo, "自定义通知标题");
		contentViews.setTextViewText(R.id.textNo, "自定义通知内容11111");

		Intent intent = new Intent(MainActivity.this,
				ResultActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				MainActivity.this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MainActivity.this).setSmallIcon(R.mipmap.push)
				.setContentTitle("My notification")
				.setTicker("new message");
		mBuilder.setAutoCancel(true);

		mBuilder.setContentIntent(pendingIntent);
		mBuilder.setContent(contentViews);
		mBuilder.setAutoCancel(true);
		mBuilder.setDefaults(Notification.DEFAULT_ALL);
		mNotificationManager.notify(10, mBuilder.build());
	}

	public void SimplestNotificationActivity(View view) {
		startActivity(new Intent(this, SimplestNotificationActivity.class));
	}

	public void NotificationEffectActivity(View view) {
		startActivity(new Intent(this, NotificationEffectActivity.class));
	}

	public void SimpleNotificationActivity(View view) {
		startActivity(new Intent(this, SimpleNotificationActivity.class));
	}

	public void NotificationStyleActivity(View view) {
		startActivity(new Intent(this, NotificationStyleActivity.class));
	}

	public void TaskStackBuilderActivity(View view) {
		startActivity(new Intent(this, TaskStackBuilderActivity.class));
	}

	public void NotificationListenerServiceActivity(View view) {
		startActivity(new Intent(this, NotificationListenerServiceActivity.class));
	}
}
