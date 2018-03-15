package com.merxury.entity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.merxury.utils.ApkUtils;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Mercury on 2017/12/30.
 * An entity class that describe simplified application information
 */

public class Application implements Parcelable {
    public static final Creator<Application> CREATOR = new Creator<Application>() {
        @Override
        public Application createFromParcel(Parcel source) {
            return new Application(source);
        }

        @Override
        public Application[] newArray(int size) {
            return new Application[size];
        }
    };
    private String packageName;
    private String versionName;
    private int versionCode;
    private boolean enabled;
    private String appName;
    private int targetSdkVersion;
    private int minSdkVersion;
    private String nonLocalizedLabel;
    private String sourceDir;
    private String publicSourceDir;
    private String[] splitNames;
    private String dataDir;

    private Application() {
    }

    public Application(@NonNull PackageManager pm, @NonNull PackageInfo info) {
        this(info);
        ApplicationInfo appDetail = info.applicationInfo;
        this.targetSdkVersion = appDetail.targetSdkVersion;
        this.nonLocalizedLabel = appDetail.nonLocalizedLabel.toString();
        this.sourceDir = appDetail.sourceDir;
        this.publicSourceDir = appDetail.sourceDir;
        this.dataDir = appDetail.dataDir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.minSdkVersion = appDetail.minSdkVersion;
        } else {
            ApkUtils.getMinSdkVersion(new File(""));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.splitNames = appDetail.splitNames;
        } else {
            this.splitNames = this.packageName.split("\\.");
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(int targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public Application(@NonNull PackageInfo info) {
        this.packageName = info.packageName;
        this.versionName = info.versionName;
        this.versionCode = info.versionCode;
        ApplicationInfo appDetails = info.applicationInfo;
        if (appDetails != null) {
            this.targetSdkVersion = appDetails.targetSdkVersion;
            this.enabled = appDetails.enabled;
        }
    }

    protected Application(Parcel in) {
        this.appName = in.readString();
        this.packageName = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readInt();
        this.enabled = in.readByte() != 0;
        this.minSdkVersion = in.readInt();
        this.targetSdkVersion = in.readInt();
        this.nonLocalizedLabel = in.readString();
        this.sourceDir = in.readString();
        this.publicSourceDir = in.readString();
        this.splitNames = in.createStringArray();
        this.dataDir = in.readString();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(int minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public String getNonLocalizedLabel() {
        return nonLocalizedLabel;
    }

    public void setNonLocalizedLabel(String nonLocalizedLabel) {
        this.nonLocalizedLabel = nonLocalizedLabel;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getPublicSourceDir() {
        return publicSourceDir;
    }

    public void setPublicSourceDir(String publicSourceDir) {
        this.publicSourceDir = publicSourceDir;
    }

    public String[] getSplitNames() {
        return splitNames;
    }

    public void setSplitNames(String[] splitNames) {
        this.splitNames = splitNames;
    }

    public String getDataDir() {
        return dataDir;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", enabled=" + enabled +
                ", minSdkVersion=" + minSdkVersion +
                ", targetSdkVersion=" + targetSdkVersion +
                ", nonLocalizedLabel='" + nonLocalizedLabel + '\'' +
                ", sourceDir='" + sourceDir + '\'' +
                ", publicSourceDir='" + publicSourceDir + '\'' +
                ", splitNames=" + Arrays.toString(splitNames) +
                ", dataDir='" + dataDir + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.packageName);
        dest.writeString(this.versionName);
        dest.writeInt(this.versionCode);
        dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
        dest.writeInt(this.minSdkVersion);
        dest.writeInt(this.targetSdkVersion);
        dest.writeString(this.nonLocalizedLabel);
        dest.writeString(this.sourceDir);
        dest.writeString(this.publicSourceDir);
        dest.writeStringArray(this.splitNames);
        dest.writeString(this.dataDir);
    }
}
