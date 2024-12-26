package com.xuhao.payroll.infrastructure.common.util;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * 文件对话框工具类
 */
public class FileDialogUtil {
    private FileDialogUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 选择保存PDF文件的路径
     * 
     * @param window          父窗口
     * @param defaultFileName 默认文件名
     * @return 选择的文件路径，如果取消则返回null
     */
    public static String choosePdfSavePath(Window window, String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存PDF文件");
        fileChooser.setInitialFileName(defaultFileName);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF文件", "*.pdf"));

        File file = fileChooser.showSaveDialog(window);
        return file != null ? file.getAbsolutePath() : null;
    }

    /**
     * 选择保存Excel文件的路径
     * 
     * @param window          父窗口
     * @param defaultFileName 默认文件名
     * @return 选择的文件路径，如果取消则返回null
     */
    public static String chooseExcelSavePath(Window window, String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存Excel文件");
        fileChooser.setInitialFileName(defaultFileName);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel文件", "*.xlsx"));

        File file = fileChooser.showSaveDialog(window);
        return file != null ? file.getAbsolutePath() : null;
    }

    /**
     * 选择目录
     * 
     * @param window 父窗口
     * @param title  对话框标题
     * @return 选择的目录路径，如果取消则返回null
     */
    public static String chooseDirectory(Window window, String title) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);

        File dir = directoryChooser.showDialog(window);
        return dir != null ? dir.getAbsolutePath() : null;
    }

    /**
     * 选择Excel文件导入
     * 
     * @param window 父窗口
     * @return 选择的文件路径，如果取消则返回null
     */
    public static String chooseExcelImportFile(Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择Excel文件");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel文件", "*.xlsx", "*.xls"));

        File file = fileChooser.showOpenDialog(window);
        return file != null ? file.getAbsolutePath() : null;
    }
}