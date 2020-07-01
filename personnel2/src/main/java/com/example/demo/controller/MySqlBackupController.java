package com.example.demo.controller;

/**
 * Description：
 * DATE:2020/5/14 20:51
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.example.demo.entity.BackupDataSourceProperties;
import com.example.demo.entity.Result;
import com.example.demo.entity.Sys;
import com.example.demo.service.MysqlBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.BackupConstants;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 *  系统数据备份还原
 */

@RestController
@RequestMapping("/backupdatabsase")
public class MySqlBackupController {

    @Autowired
    MysqlBackupService mysqlBackupService;
    @Autowired
    BackupDataSourceProperties properties;

    @RequestMapping(value = "/backup", method = POST ,produces = "application/json;charset=UTF-8")//数据库备份备份
    public Result backup() {
        Result result = new Result();
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        //String backupFodlerName = BackupConstants.DEFAULT_BACKUP_NAME+ "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
        String backupFolderPath = BackupConstants.BACKUP_FOLDER  + File.separator;
        String fileName = BackupConstants.BACKUP_FILE_NAME;
        try {
            mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
            result.setMsg("数据库备份成功！！！");

        } catch (Exception e) {
           // e.printStackTrace();
            result.setState(false).setMsg("数据库备份失败！");
        }
        return result;
    }

    @RequestMapping(value = "/restore", method = POST ,produces = "application/json;charset=UTF-8")//数据库还原
    public Result restore() {

        Result result = new Result();
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + "personnel.sql";
        try {
            mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
            result.setMsg("数据库还原成功！！！");
        } catch (Exception e) {
            result.setState(false).setMsg("数据库还原失败！");
        }
        return result;
    }

   /** @GetMapping("/findRecords")
    public HttpResult findBackupRecords() {
        List<Map<String, Object>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if(restoreFolderFile.exists()) {
            for(File file:restoreFolderFile.listFiles()) {
                Map<String, Object> bean = new HashMap<>();
                bean.put("name", file.getName());
                bean.put("title", file.getName());
                if(BackupConstants.DEFAULT_BACKUP_NAME.equals(file.getName())) {
                    bean.put("title", "系统默认备份");
                }
                backupRecords.add(bean);
            }
        }
        return HttpResult.ok(backupRecords);
    }

    @GetMapping("/delete")
    public HttpResult deleteBackupRecord(@RequestParam String name) {
        if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {
            return HttpResult.error("系统默认备份无法删除!");
        }
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
        try {
            FileUtils.deleteFile(new File(restoreFilePath));
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }*/

}
