package com.fanneng.common.utils;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtils {
    /**
     * 判断是否有sd卡
     *
     * @return true 有sd卡
     * */
    public static boolean hasSdcard() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 过滤文件并删除文件
     * @param dir 目录
     * @param filenameFilter
     * */
    public static void delete(String dir, FilenameFilter filenameFilter) {
        File f = new File(dir);
        String[] files = f.list(filenameFilter);
        if(files!=null && files.length>0) {
            for (String file : files) {
                deleteFile(file);
            }
        }
    }

    /**
     * 删除单个文件
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
