package com.xuhao.payroll.infrastructure.task;

import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.xuhao.payroll.infrastructure.common.util.SessionUtil;

/**
 * 会话清理定时任务
 */
public class SessionCleanupTask {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final long CLEANUP_INTERVAL = 5; // 5分钟清理一次

    private SessionCleanupTask() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 启动定时清理任务
     */
    public static void startCleanupTask() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // 在JavaFX应用程序线程中执行清理操作
                Platform.runLater(SessionUtil::cleanExpiredSessions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, CLEANUP_INTERVAL, CLEANUP_INTERVAL, TimeUnit.MINUTES);
    }

    /**
     * 停止定时清理任务
     */
    public static void stopCleanupTask() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}