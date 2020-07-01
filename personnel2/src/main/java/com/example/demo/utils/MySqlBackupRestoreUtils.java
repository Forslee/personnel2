package com.example.demo.utils;

import java.io.*;

/**
 * MySQL备份还原工具类
 * @author Louis
 * @date Sep 21, 2018
 */
public class MySqlBackupRestoreUtils {

    /**
     * Java代码实现MySQL数据库导出
     *
     * @author cf
     * @param hostIP
     *            MySQL数据库所在服务器地址IP
     * @param userName
     *            进入数据库所需要的用户名
     * @param password
     *            进入数据库所需要的密码
     * @param savePath
     *            数据库导出文件保存路径
     * @param fileName
     *            数据库导出文件文件名
     * @param dbOrTbName
     *            要导出的数据库名 表名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName, String dbOrTbName)

            throws InterruptedException {
        File saveFile = new File(savePath);

        if (!saveFile.exists()) {// 如果目录不存在
             saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }


        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec("mysqldump -h" +hostIP+
                    " -u"+userName+" -p"+password+" --set-charset=UTF8 "+dbOrTbName);
            InputStreamReader inputStreamReader = new InputStreamReader(
                    process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                System.out.println("数据已经备份到 " +savePath + " 文件中");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 还原数据库
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host IP地址
     * @param database 数据库名称
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database)
            throws Exception {
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h" + host);
        stringBuilder.append(" -u" + userName);
        stringBuilder.append(" -p" + password);
        stringBuilder.append(" " + database + " <");
        stringBuilder.append(restoreFilePath);
        System.out.println("cmd命令为：" + stringBuilder.toString());
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("cmd /c" + stringBuilder.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "utf8"));
            String line = null;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
            if (process.waitFor() == 0)
            {
                System.out.println("数据已从 " + restoreFilePath + " 导入到数据库中");
                return true;
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/sh";
        if(os.toLowerCase().startsWith("win")){
            shell = "cmd";
        }
        String[] cmd = { shell, "/c", command };
        return cmd;
    }
}