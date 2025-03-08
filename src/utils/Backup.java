package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Backup {
    static void backupTable() {
        String tableName = "products";
        String pgDump = "C:\\Program Files\\PostgreSQL\\17\\bin\\pg_dump.exe";
        String username = "postgres";
        String password = "123";
        String database = "miniproject";
        String backupDir = "src\\resources\\backups\\";
        String backupFile = getNextBackupFilename(backupDir);
        ProcessBuilder processBuilder = new ProcessBuilder(
                pgDump,
                "-U", username,
                "-d", database,
                "-t", tableName,
                "-F", "p",
                "--clean","--if-exists",
                "-f", backupFile,
                "-v"
        );

        // Avoid password prompt
        processBuilder.environment().put("PGPASSWORD", password);


        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                Helper.printSuccessMsg("Data backup successful!");
            } else {
                System.err.println("Backup failed!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void restoreDatabase(String restorePath) {
        String pgRestore = "C:\\Program Files\\PostgreSQL\\17\\bin\\psql.exe";
        String username = "postgres";
        String password = "123";
        String database = "miniproject";
        restorePath = "src\\resources\\backups\\" + restorePath;

        ProcessBuilder processBuilder = new ProcessBuilder(
                pgRestore,
                "-U", username,
                "-d", database,
                "--no-password",
                "-f", restorePath
        );
        processBuilder.environment().put("PGPASSWORD", password);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                if (Files.exists(Paths.get(restorePath))) {
                   Helper.printSuccessMsg("Database restored successful");
                } else {
                    System.err.println("Restore failed: File not found!");
                }
            } else {
                System.err.println("Restore failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String getNextBackupFilename(String backupDir) {
        File dir = new File(backupDir);
        LocalDate currenDate = LocalDate.now();
        if (!dir.exists()) dir.mkdirs();

        int counter = 1;
        File file;
        do {
            file = new File(backupDir + "Version" + counter + "-product-backup-" + currenDate + ".sql");
            counter++;
        } while (file.exists());
        return file.getAbsolutePath();
    }

    static List<String> getBackUpFileName() {
        List<String> fileList = new ArrayList<>();
        String path = "src/resources/backups";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }
        return fileList;
    }
}
