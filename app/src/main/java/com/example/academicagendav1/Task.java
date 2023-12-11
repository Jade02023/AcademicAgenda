package com.example.academicagendav1;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.view.ViewGroup;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

public abstract class Task {
    public static String Tasks = "";
    protected final ArrayList<Object> tasks;

    public Task(String tasks) {
        Tasks = tasks;
        this.tasks = new ArrayList<>();
    }

    public abstract int getItemCount();

    public abstract void onBindViewHolder(TaskViewHolder holder, int position);

    @NonNull
    public abstract TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public String getTasks() {
        return Tasks;
    }

    public void addTask(String taskId, String taskName, Date dueDate) {
        TaskItem taskItem = new TaskItem(taskId, taskName, dueDate);
        tasks.add(taskItem);
        scheduleTaskNotification(taskItem);
    }

    // Method to delete a task
    public void deleteTask(String taskId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tasks.removeIf(taskItem -> {
                boolean equals = Objects.equals(taskItem, taskId);
                return equals;

            });
        }
        // Cancel any existing notifications for the deleted task
        cancelTaskNotification(taskId);
    }

    public abstract void onBindViewHolder(@NonNull com.example.academicagendav1.Tasks.Tasks.TaskViewHolder holder, int position);

    public static class TaskItem {
        private final String taskID;
        private final String taskName;
        private final Date dueDate;

        public TaskItem(String taskName, String name, Date dueDate) {
            this.taskID = UUID.randomUUID().toString();
            this.taskName = taskName;
            this.dueDate = dueDate;
        }

        public String getTaskID() {
            return taskID;
        }

        public String getTaskName() {
            return taskName;
        }

        public Date getDueDate() {
            return dueDate;
        }
    }
    // Method to set notifications for tasks
    private void scheduleTaskNotification(TaskItem taskItem) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Implement logic to trigger the notification
                System.out.println("Notification: Task '" + taskItem.taskName + "' is due!");
            }
        }, taskItem.dueDate);
    }

    // Method to cancel a scheduled notification for a task
    private void cancelTaskNotification(String taskId) {
        System.out.println("Notification canceled for Task with ID: " + taskId);
    }

    public AssetManager getAssets() {
        return null;
    }

    public abstract Resources getResources();

    public abstract PackageManager getPackageManager();

    public abstract ContentResolver getContentResolver();

    public abstract Looper getMainLooper();

    public abstract Context getApplicationContext();

    public abstract void setTheme(int resid);

    public abstract Resources.Theme getTheme();

    public abstract ClassLoader getClassLoader();

    public abstract String getPackageName();

    public abstract ApplicationInfo getApplicationInfo();

    public abstract String getPackageResourcePath();

    public abstract String getPackageCodePath();

    public abstract SharedPreferences getSharedPreferences(String name, int mode);

    public abstract boolean moveSharedPreferencesFrom(Context sourceContext, String name);

    public abstract boolean deleteSharedPreferences(String name);

    public abstract FileInputStream openFileInput(String name) throws FileNotFoundException;

    public abstract FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException;

    public abstract boolean deleteFile(String name);

    public abstract File getFileStreamPath(String name);

    public abstract File getDataDir();

    public abstract File getFilesDir();

    public abstract File getNoBackupFilesDir();

    @Nullable
    public abstract File getExternalFilesDir(@Nullable String type);

    public abstract File[] getExternalFilesDirs(String type);

    public abstract File getObbDir();

    public abstract File[] getObbDirs();

    public abstract File getCacheDir();

    public abstract File getCodeCacheDir();

    @Nullable
    public abstract File getExternalCacheDir();

    public abstract File[] getExternalCacheDirs();

    public abstract File[] getExternalMediaDirs();

    public abstract String[] fileList();

    public abstract File getDir(String name, int mode);

    public abstract SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory);



    public abstract SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, @Nullable DatabaseErrorHandler errorHandler);

    public abstract boolean moveDatabaseFrom(Context sourceContext, String name);

    public abstract boolean deleteDatabase(String name);

    public abstract File getDatabasePath(String name);

    public abstract String[] databaseList();

    public abstract Drawable getWallpaper();

    public abstract Drawable peekWallpaper();

    public abstract int getWallpaperDesiredMinimumWidth();

    public abstract int getWallpaperDesiredMinimumHeight();

    public abstract void setWallpaper(Bitmap bitmap) throws IOException;

    public abstract void setWallpaper(InputStream data) throws IOException;

    public abstract void clearWallpaper() throws IOException;

    public abstract void startActivity(Intent intent, @Nullable Bundle options);

    public abstract void startActivities(Intent[] intents);

    public abstract void startActivities(Intent[] intents, Bundle options);

    public abstract void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException;

    public abstract void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException;

    public abstract void sendBroadcast(Intent intent);

    public abstract void sendBroadcast(Intent intent, @Nullable String receiverPermission);

    public abstract void sendOrderedBroadcast(Intent intent, @Nullable String receiverPermission);

    public abstract void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String receiverPermission, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle user);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission);

    public abstract void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras);

    public abstract void sendStickyBroadcast(Intent intent);

    public abstract void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras);

    public abstract void removeStickyBroadcast(Intent intent);

    public abstract void sendStickyBroadcastAsUser(Intent intent, UserHandle user);

    public abstract void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras);

    public abstract void removeStickyBroadcastAsUser(Intent intent, UserHandle user);

    @Nullable
    public abstract Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter);

    @Nullable
    public abstract Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter, int flags);

    @Nullable
    public abstract Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler);

    @Nullable
    public abstract Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler, int flags);

    public abstract void unregisterReceiver(BroadcastReceiver receiver);

    @Nullable
    public abstract ComponentName startService(Intent service);

    @Nullable
    public abstract ComponentName startForegroundService(Intent service);

    public abstract boolean stopService(Intent service);

    public abstract boolean bindService(@NonNull Intent service, @NonNull ServiceConnection conn, int flags);

    public abstract void unbindService(@NonNull ServiceConnection conn);

    public abstract boolean startInstrumentation(@NonNull ComponentName className, @Nullable String profileFile, @Nullable Bundle arguments);

    public abstract Object getSystemService(@NonNull String name);

    @Nullable
    public abstract String getSystemServiceName(@NonNull Class<?> serviceClass);

    public abstract int checkPermission(@NonNull String permission, int pid, int uid);

    public abstract int checkCallingPermission(@NonNull String permission);

    public abstract int checkCallingOrSelfPermission(@NonNull String permission);

    public abstract int checkSelfPermission(@NonNull String permission);

    public abstract void enforcePermission(@NonNull String permission, int pid, int uid, @Nullable String message);

    public abstract void enforceCallingPermission(@NonNull String permission, @Nullable String message);

    public abstract void enforceCallingOrSelfPermission(@NonNull String permission, @Nullable String message);

    public abstract void grantUriPermission(String toPackage, Uri uri, int modeFlags);

    public abstract void revokeUriPermission(Uri uri, int modeFlags);

    public abstract void revokeUriPermission(String toPackage, Uri uri, int modeFlags);

    public abstract int checkUriPermission(Uri uri, int pid, int uid, int modeFlags);

    public abstract int checkCallingUriPermission(Uri uri, int modeFlags);

    public abstract int checkCallingOrSelfUriPermission(Uri uri, int modeFlags);

    public abstract int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags);

    public abstract void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message);

    public abstract void enforceCallingUriPermission(Uri uri, int modeFlags, String message);

    public abstract void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message);

    public abstract void enforceUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags, @Nullable String message);

    public abstract Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException;

    public abstract Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException;

    public abstract Context createConfigurationContext(@NonNull Configuration overrideConfiguration);

    public abstract Context createDisplayContext(@NonNull Display display);

    public abstract Context createDeviceProtectedStorageContext();

    public abstract boolean isDeviceProtectedStorage();

    public abstract void onBindViewHolder(com.example.academicagendav1.Tasks.TaskViewHolder holder, int position);



    public static class NotificationUtils {

        // This method shows creation and display a notification
        public void showNotification(Context context, int notificationId, String title, String content) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, builder.build());
        }
    }

    public class TaskManager extends Context {
        private List<com.google.android.gms.tasks.Task> tasks;
        private AlarmManager alarmManager;

        public TaskManager(Context context) {
            tasks = new ArrayList<>();
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            // Initialize tasks, add tasks to the list
            tasks.add(new com.google.android.gms.tasks.Task() {
                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @Nullable
                @Override
                public Exception getException() {
                    return null;
                }

                @Override
                public Object getResult() {
                    return null;
                }

                @Override
                public boolean isCanceled() {
                    return false;
                }

                @Override
                public boolean isComplete() {
                    return false;
                }

                @Override
                public boolean isSuccessful() {
                    return false;
                }

                @Override
                public Object getResult(@NonNull Class aClass) throws Throwable {
                    return null;
                }
            });
            tasks.add(new com.google.android.gms.tasks.Task() {
                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @NonNull
                @Override
                public com.google.android.gms.tasks.Task addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener onSuccessListener) {
                    return null;
                }

                @Nullable
                @Override
                public Exception getException() {
                    return null;
                }

                @Override
                public Object getResult() {
                    return null;
                }

                @Override
                public boolean isCanceled() {
                    return false;
                }

                @Override
                public boolean isComplete() {
                    return false;
                }

                @Override
                public boolean isSuccessful() {
                    return false;
                }

                @Override
                public Object getResult(@NonNull Class aClass) throws Throwable {
                    return null;
                }
            });
            public void scheduleTask(TaskItem, taskItem) {
                // Use 'this' as the context if this method is called from an Activity
                Intent intent = new Intent(this, Tasks.TaskAlarmReceiver.class);
                intent.putExtra("TASK_NAME", taskItem.getTaskName());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, taskItem.getDueDate().getTime(), pendingIntent);
                }
            }


            public class TaskManager {
        private List<Task> tasks;

        public TaskManager() {
            tasks = new ArrayList<>();
            // Initialize tasks, add tasks to the list
            tasks.add(new Task("Task 1", "Description 1"));
            tasks.add(new Task("Task 2", "Description 2"));
            // Add more tasks as needed
        }

        // Method to search and return tasks based on a search term
        public List<Task> searchTasks(String searchTerm) {
            List<Task> matchingTasks = new ArrayList<>();

            for (Task task : tasks) {
                // Implement search logic here
                if (task.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }

            return matchingTasks;
        }

        // Example Task class
        private class Task {
            private String name;
            private String description;

            public Task(String name, String description) {
                this.name = name;
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }
        }

        public void main(String[] args) {
            Tasks.TaskManager taskManager = new Tasks.TaskManager();

            // Example search
            List<Task> searchResults = taskManager.searchTasks("Task 1");

            // Display search results
            for (Task task : searchResults) {
                System.out.println("Task Name: " + task.getName() + ", Description: " + task.getDescription());
            }
        }
    }

    // BroadcastReceiver to handle alarms
    public class TaskAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String taskName = intent.getStringExtra("TASK_NAME");
            NotificationUtils notificationUtils = new NotificationUtils();
            notificationUtils.showNotification(context, 1, "Task Reminder", "Task '" + taskName + "' is due!");
        }
    }
}

        @Override
        public AssetManager getAssets() {
            return null;
        }

        @Override
        public Resources getResources() {
            return null;
        }

        @Override
        public PackageManager getPackageManager() {
            return null;
        }

        @Override
        public ContentResolver getContentResolver() {
            return null;
        }

        @Override
        public Looper getMainLooper() {
            return null;
        }

        @Override
        public Context getApplicationContext() {
            return null;
        }

        @Override
        public void setTheme(int i) {

        }

        @Override
        public Resources.Theme getTheme() {
            return null;
        }

        @Override
        public ClassLoader getClassLoader() {
            return null;
        }

        @Override
        public String getPackageName() {
            return null;
        }

        @Override
        public ApplicationInfo getApplicationInfo() {
            return null;
        }

        @Override
        public String getPackageResourcePath() {
            return null;
        }

        @Override
        public String getPackageCodePath() {
            return null;
        }

        @Override
        public SharedPreferences getSharedPreferences(String s, int i) {
            return null;
        }

        @Override
        public boolean moveSharedPreferencesFrom(Context context, String s) {
            return false;
        }

        @Override
        public boolean deleteSharedPreferences(String s) {
            return false;
        }

        @Override
        public FileInputStream openFileInput(String s) throws FileNotFoundException {
            return null;
        }

        @Override
        public FileOutputStream openFileOutput(String s, int i) throws FileNotFoundException {
            return null;
        }

        @Override
        public boolean deleteFile(String s) {
            return false;
        }

        @Override
        public File getFileStreamPath(String s) {
            return null;
        }

        @Override
        public File getDataDir() {
            return null;
        }

        @Override
        public File getFilesDir() {
            return null;
        }

        @Override
        public File getNoBackupFilesDir() {
            return null;
        }

        @Nullable
        @Override
        public File getExternalFilesDir(@Nullable String s) {
            return null;
        }

        @Override
        public File[] getExternalFilesDirs(String s) {
            return new File[0];
        }

        @Override
        public File getObbDir() {
            return null;
        }

        @Override
        public File[] getObbDirs() {
            return new File[0];
        }

        @Override
        public File getCacheDir() {
            return null;
        }

        @Override
        public File getCodeCacheDir() {
            return null;
        }

        @Nullable
        @Override
        public File getExternalCacheDir() {
            return null;
        }

        @Override
        public File[] getExternalCacheDirs() {
            return new File[0];
        }

        @Override
        public File[] getExternalMediaDirs() {
            return new File[0];
        }

        @Override
        public String[] fileList() {
            return new String[0];
        }

        @Override
        public File getDir(String s, int i) {
            return null;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory) {
            return null;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory, @Nullable DatabaseErrorHandler databaseErrorHandler) {
            return null;
        }

        @Override
        public boolean moveDatabaseFrom(Context context, String s) {
            return false;
        }

        @Override
        public boolean deleteDatabase(String s) {
            return false;
        }

        @Override
        public File getDatabasePath(String s) {
            return null;
        }

        @Override
        public String[] databaseList() {
            return new String[0];
        }

        @Override
        public Drawable getWallpaper() {
            return null;
        }

        @Override
        public Drawable peekWallpaper() {
            return null;
        }

        @Override
        public int getWallpaperDesiredMinimumWidth() {
            return 0;
        }

        @Override
        public int getWallpaperDesiredMinimumHeight() {
            return 0;
        }

        @Override
        public void setWallpaper(Bitmap bitmap) throws IOException {

        }

        @Override
        public void setWallpaper(InputStream inputStream) throws IOException {

        }

        @Override
        public void clearWallpaper() throws IOException {

        }

        @Override
        public void startActivity(Intent intent) {

        }

        @Override
        public void startActivity(Intent intent, @Nullable Bundle bundle) {

        }

        @Override
        public void startActivities(Intent[] intents) {

        }

        @Override
        public void startActivities(Intent[] intents, Bundle bundle) {

        }

        @Override
        public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2) throws IntentSender.SendIntentException {

        }

        @Override
        public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2, @Nullable Bundle bundle) throws IntentSender.SendIntentException {

        }

        @Override
        public void sendBroadcast(Intent intent) {

        }

        @Override
        public void sendBroadcast(Intent intent, @Nullable String s) {

        }

        @Override
        public void sendOrderedBroadcast(Intent intent, @Nullable String s) {

        }

        @Override
        public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String s, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

        }

        @Override
        public void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {

        }

        @Override
        public void sendBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s) {

        }

        @Override
        public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

        }

        @Override
        public void sendStickyBroadcast(Intent intent) {

        }

        @Override
        public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

        }

        @Override
        public void removeStickyBroadcast(Intent intent) {

        }

        @Override
        public void sendStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

        }

        @Override
        public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

        }

        @Override
        public void removeStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

        }

        @Nullable
        @Override
        public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler, int i) {
            return null;
        }

        @Override
        public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {

        }

        @Nullable
        @Override
        public ComponentName startService(Intent intent) {
            return null;
        }

        @Nullable
        @Override
        public ComponentName startForegroundService(Intent intent) {
            return null;
        }

        @Override
        public boolean stopService(Intent intent) {
            return false;
        }

        @Override
        public boolean bindService(Intent intent, @NonNull ServiceConnection serviceConnection, int i) {
            return false;
        }

        @Override
        public void unbindService(@NonNull ServiceConnection serviceConnection) {

        }

        @Override
        public boolean startInstrumentation(@NonNull ComponentName componentName, @Nullable String s, @Nullable Bundle bundle) {
            return false;
        }

        @Override
        public Object getSystemService(@NonNull String s) {
            return null;
        }

        @Nullable
        @Override
        public String getSystemServiceName(@NonNull Class<?> aClass) {
            return null;
        }

        @Override
        public int checkPermission(@NonNull String s, int i, int i1) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkCallingPermission(@NonNull String s) {
            return 0;
        }

        @Override
        public int checkCallingOrSelfPermission(@NonNull String s) {
            return 0;
        }

        @Override
        public int checkSelfPermission(@NonNull String s) {
            return 0;
        }

        @Override
        public void enforcePermission(@NonNull String s, int i, int i1, @Nullable String s1) {

        }

        @Override
        public void enforceCallingPermission(@NonNull String s, @Nullable String s1) {

        }

        @Override
        public void enforceCallingOrSelfPermission(@NonNull String s, @Nullable String s1) {

        }

        @Override
        public void grantUriPermission(String s, Uri uri, int i) {

        }

        @Override
        public void revokeUriPermission(Uri uri, int i) {

        }

        @Override
        public void revokeUriPermission(String s, Uri uri, int i) {

        }

        @Override
        public int checkUriPermission(Uri uri, int i, int i1, int i2) {
            return 0;
        }

        @Override
        public int checkCallingUriPermission(Uri uri, int i) {
            return 0;
        }

        @Override
        public int checkCallingOrSelfUriPermission(Uri uri, int i) {
            return 0;
        }

        @Override
        public int checkUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2) {
            return 0;
        }

        @Override
        public void enforceUriPermission(Uri uri, int i, int i1, int i2, String s) {

        }

        @Override
        public void enforceCallingUriPermission(Uri uri, int i, String s) {

        }

        @Override
        public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s) {

        }

        @Override
        public void enforceUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2, @Nullable String s2) {

        }

        @Override
        public Context createPackageContext(String s, int i) throws PackageManager.NameNotFoundException {
            return null;
        }

        @Override
        public Context createContextForSplit(String s) throws PackageManager.NameNotFoundException {
            return null;
        }

        @Override
        public Context createConfigurationContext(@NonNull Configuration configuration) {
            return null;
        }

        @Override
        public Context createDisplayContext(@NonNull Display display) {
            return null;
        }

        @Override
        public Context createDeviceProtectedStorageContext() {
            return null;
        }

        @Override
        public boolean isDeviceProtectedStorage() {
            return false;
        }
    }
