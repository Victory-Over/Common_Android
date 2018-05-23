package com.fanneng.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

public class FileUtils {
    public static final String tag = "FileUtils";

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
                delFile(file);
            }
        }
    }

    /**
     * 删除单个文件
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean delFile(String fileName) {
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

    /**
     * 从文件读入输入流
     * */
    public static InputStream getFileInputStream(String filename) {
        InputStream inputstream = null;
        if (null == filename || filename.length() == 0) {
            return null;
        }

        try {
            File pfile = new File(filename);
            if (pfile.exists() && (!pfile.isDirectory())) {
                inputstream = new FileInputStream(pfile);
            }
        } catch (IOException e) {
            Log.e(tag, "getFileInputStream(): " + e.getMessage());
            return null;
        }

        return inputstream;
    }

    /**
     * 复制文件
     * */
    public static boolean copyFile(File srcFile, File destFile) {
        //先经过错误过滤
        if ((null == srcFile) || (null == destFile) || !srcFile.exists() || srcFile.isDirectory()) {
            return false;
        }

        if (!destFile.exists()) {
            destFile = createFile(destFile.getAbsolutePath());
            return false;
        }

        boolean isOK = true;
        FileChannel out = null;
        FileChannel in = null;
        try {
            out = new FileOutputStream(destFile).getChannel();
            in = new FileInputStream(srcFile).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(102400);
            int position = 0;
            int length = 0;
            while (true) {
                length = in.read(buffer, position);
                if (length <= 0) {
                    break;
                }
                buffer.flip();
                out.write(buffer, position);
                position += length;
                buffer.clear();
            }
        } catch (Exception e) {
            isOK = false;
            Log.e(tag, "copyFile(): " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    Log.e(tag, "copyFile(): " + e.getMessage());
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(tag, "copyFile(): " + e.getMessage());
                }
            }
        }
        return isOK;
    }

    /**
     * 创建文件
     *
     * @param path 文件名 以“/”开头表示绝对路径
     * @return 文件File
     */
    public static File createFile(String path) {
        if (path.startsWith("./")) {
            path = path.substring(2);
        }

        File file = null;
        // 是一个绝对路径文件
        if (path.startsWith("/")) {
            file = new File(path);
        } else {
            //file = new File(ConstUtil.FileRootPath + path);
        }

        if (file.exists()) { // 文件存在删掉存在文件
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (Exception e) {
            Log.e(tag, "createFile(): " + e.getMessage());
            try {
                String parent = file.getParent() + "/";
                File pfile = new File(parent);
                pfile.mkdirs();
                file.createNewFile();
                return file;
            } catch (Exception x) {
                Log.e(tag, "createFile(): " + e.getMessage());
                return new File("");
            }
        }

        return file;
    }

    /**
     * 删除文件或目录
     */
    public static boolean delFileOrDir(String path) {
        File f = new File(path);
        boolean ret = true;

        if (!f.exists()) {
            ret = false;
        } else if (f.exists()) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!delFileOrDir(files[i].getPath())) {
                        ret = false;
                    }
                }
            } else if (!f.delete()) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * 将bitmap保存到文件
     * */
    public static boolean saveBitmapToFile(Bitmap mBitmap, String filepath) {
        if (null == mBitmap || null == filepath || 0 == filepath.trim().length()) {
            return false;
        }

        boolean result = true;
        File file = createFile(filepath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            result = mBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e(tag, "saveBitmapToFile(): " + e.getMessage());
            result = false;
        }
        return result;
    }

    /**
     * 获取文件目录
     * 这个算法 是按字符串取的 是没有对这个文件或者目录进行验证的
     */

    public static String getFileDir(String pathstr) {
        if (!TextUtils.isEmpty(pathstr)) {
            // 获取相对路径
            int i = pathstr.lastIndexOf("/");
            return pathstr.substring(0, i);
        } else {
            return "";
        }
    }

    /**
     * 获取文件的后缀名
     * 这个算法 是按字符串取的 是没有对这个文件或者目录进行验证的  下面有个方法是读取真实的后缀名的方法
     */
    public static String getExtName(String s) {
        int i = s.lastIndexOf(".");
        int leg = s.length();
        return (i > 0 ? (i + 1) == leg ? " " : s.substring(i, s.length()) : " ");
    }


    /**
     * 获得文件路径的文件名
     *
     * @return 文件名
     */
    public static String getFileName(String path) {
        int i = path.lastIndexOf("/");
        int leg = path.length();
        return (i > 0 ? (i + 1) == leg ? "" : path.substring(i + 1, path.length()) : " ");
    }


    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG ,BMP or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        } else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        } else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        } else if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } else {
            return null;
        }
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        String mimeType;

        if ("JPG".equals(suffix)) {
            mimeType = "image/jpeg";
        } else if ("GIF".equals(suffix)) {
            mimeType = "image/gif";
        } else if ("PNG".equals(suffix)) {
            mimeType = "image/png";
        } else if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
        } else {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    /**
     * 文件重命名
     *
     * @param oldname 原来的文件名
     * @param newname 新文件名
     */
    public static boolean renameFileToPath(String oldname, String newname) {
        if (!oldname.equals(newname)) {
            File oldfile = new File(oldname);
            File newfile = new File(newname);
            if (!oldfile.exists()) {
                return false;
            }

            if (newfile.exists()) {
                return false;
            } else {
                return oldfile.renameTo(newfile);
            }
        }
        return false;
    }

    /**
     * 获取文件夹大小
     *
     * @param filePath 文件路径
     * @return 文件夹大小(单位为MB)
     */
    public static Double getFolderSize(String filePath) {
        long size = 0;
        File file = new File(filePath);

        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    size += getFolderSize(files[i].getAbsolutePath());
                } else {
                    size += files[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        double d = ((double) size) / 1024 / 1024;
        DecimalFormat df = new DecimalFormat("######0.00");
        double newd = Double.parseDouble(df.format(d));
        return newd;
    }
}
