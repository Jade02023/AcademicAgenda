package com.example.academicagendav1;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;

public abstract class  extends com.example.academicagendav1.Task {

    // Constructor
    public Tasks(String tasks) {
        super(tasks);
    }

    // Adapter for the RecyclerView
    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView taskNameTextView;
        private TextView dueDateTextView;
        private ImageView taskIconImageView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskNameTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            taskIconImageView = itemView.findViewById(R.id.taskIconImageView);
        }

        public TextView getTaskNameTextView() {
            return taskNameTextView;
        }

        public TextView getDueDateTextView() {
            return dueDateTextView;
        }

        public ImageView getTaskIconImageView() {
            return taskIconImageView;
        }

        public void bind(TaskItem taskItem) {
            taskNameTextView.setText(taskItem.getTaskName());
            dueDateTextView.setText(taskItem.getDueDate().toString());;
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onBindViewHolder(com.example.academicagendav1.TaskViewHolder holder, int position) {
        holder.bind((String) tasks.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.academicagendav1.Tasks.Tasks.TaskViewHolder holder, int position) {

    }

    @NonNull
    @Override
    public com.example.academicagendav1.Tasks.Tasks.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new com.example.academicagendav1.Tasks.Tasks.TaskViewHolder(view);
    }
}








// ViewHolder class for each task item
        class TaskViewHolder extends RecyclerView.ViewHolder {
            private final TextView taskTextView;

            protected TaskViewHolder() {
                super(itemView);
                taskTextView = itemView.findViewById(R.id.taskTextView);
            }

            // Bind data to the ViewHolder
            void bind(String task) {
                taskTextView.setText(task);
            }
        }
    }
}
    private void manageTasks() {

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
        public void setTheme(int resid) {

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
        public SharedPreferences getSharedPreferences(String name, int mode) {
            return null;
        }

        @Override
        public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
            return false;
        }

        @Override
        public boolean deleteSharedPreferences(String name) {
            return false;
        }

        @Override
        public FileInputStream openFileInput(String name) throws FileNotFoundException {
            return null;
        }

        @Override
        public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
            return null;
        }

        @Override
        public boolean deleteFile(String name) {
            return false;
        }

        public File getFileStreamPath(String name) {
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
        public File getExternalFilesDir(@Nullable String type) {
            return null;
        }

        @Override
        public File[] getExternalFilesDirs(String type) {
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
        public File getDir(String name, int mode) {
            return null;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
            return null;
        }


        public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, @Nullable DatabaseErrorHandler errorHandler) {
            return null;
        }

        @Override
        public boolean moveDatabaseFrom(Context sourceContext, String name) {
            return false;
        }

        @Override
        public boolean deleteDatabase(String name) {
            return false;
        }

        @Override
        public File getDatabasePath(String name) {
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
        public void setWallpaper(InputStream data) throws IOException {

        }


        public void startActivity(Intent intent) {

        }

        @Override
        public void startActivity(Intent intent, @Nullable Bundle options) {

        }

        @Override
        public void startActivities(Intent[] intents) {

        }

        @Override
        public void startActivities(Intent[] intents, Bundle options) {

        }

        @Override
        public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {

        }

        @Override
        public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {

        }

        @Override
        public void sendBroadcast(Intent intent) {

        }

        @Override
        public void sendBroadcast(Intent intent, @Nullable String receiverPermission) {

        }

        @Override
        public void sendOrderedBroadcast(Intent intent, @Nullable String receiverPermission) {

        }

        @Override
        public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String receiverPermission, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

        }

        @Override
        public void sendBroadcastAsUser(Intent intent, UserHandle user) {

        }

        @Override
        public void sendBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission) {

        }

        @Override
        public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

        }

        @Override
        public void sendStickyBroadcast(Intent intent) {

        }

        @Override
        public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

        }

        @Override
        public void removeStickyBroadcast(Intent intent) {

        }

        @Override
        public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {

        }

        @Override
        public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

        }

        @Override
        public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {

        }

        @Nullable
        @Override
        public Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter, int flags) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler) {
            return null;
        }

        @Nullable
        @Override
        public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler, int flags) {
            return null;
        }

        @Override
        public void unregisterReceiver(BroadcastReceiver receiver) {

        }

        @Nullable
        @Override
        public ComponentName startService(Intent service) {
            return null;
        }

        @Nullable
        @Override
        public ComponentName startForegroundService(Intent service) {
            return null;
        }

        @Override
        public boolean stopService(Intent service) {
            return false;
        }

        @Override
        public boolean bindService(@NonNull Intent service, @NonNull ServiceConnection conn, int flags) {
            return false;
        }

        @Override
        public void unbindService(@NonNull ServiceConnection conn) {

        }

        @Override
        public boolean startInstrumentation(@NonNull ComponentName className, @Nullable String profileFile, @Nullable Bundle arguments) {
            return false;
        }

        @Override
        public Object getSystemService(@NonNull String name) {
            return null;
        }

        @Nullable
        @Override
        public String getSystemServiceName(@NonNull Class<?> serviceClass) {
            return null;
        }

        @Override
        public int checkPermission(@NonNull String permission, int pid, int uid) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkCallingPermission(@NonNull String permission) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkCallingOrSelfPermission(@NonNull String permission) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkSelfPermission(@NonNull String permission) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public void enforcePermission(@NonNull String permission, int pid, int uid, @Nullable String message) {

        }

        @Override
        public void enforceCallingPermission(@NonNull String permission, @Nullable String message) {

        }

        @Override
        public void enforceCallingOrSelfPermission(@NonNull String permission, @Nullable String message) {

        }

        @Override
        public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {

        }

        @Override
        public void revokeUriPermission(Uri uri, int modeFlags) {

        }

        @Override
        public void revokeUriPermission(String toPackage, Uri uri, int modeFlags) {

        }

        @Override
        public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkCallingUriPermission(Uri uri, int modeFlags) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags) {
            return PackageManager.PERMISSION_GRANTED;
        }

        @Override
        public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {

        }

        @Override
        public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {

        }

        @Override
        public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {

        }

        @Override
        public void enforceUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags, @Nullable String message) {

        }

        @Override
        public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
            return null;
        }

        @Override
        public Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException {
            return null;
        }

        @Override
        public Context createConfigurationContext(@NonNull Configuration overrideConfiguration) {
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

