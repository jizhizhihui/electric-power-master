package com.electricPower.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class PathUtils {

    /**
     * 如果已打成jar包，则返回jar包所在目录
     * 如果未打成jar，则返回target所在目录
     * @return
     */
    public static String getRootPath() throws UnsupportedEncodingException {
        // 项目的编译文件的根目录
        String path = URLDecoder.decode(PathUtils.class.getResource("/").getPath(), String.valueOf(StandardCharsets.UTF_8));
        if (path.startsWith("file:")) {
            int i = path.indexOf(".jar!");
            path = path.substring(0, i);
            path = path.replaceFirst("file:", "");
        }
        // 项目所在的目录
        return new File(path).getParentFile().getAbsolutePath();
    }

    /**
     * 获取项目所在根目录
     * 如果未带打包成jar包，则得到src所在目录
     * 如果打jar包，则得到jar包所在目录
     *
     * @return
     */
    public static String getBasePath() {
        File file = new File("");
        return file.getAbsolutePath();
    }
}
