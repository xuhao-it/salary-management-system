package com.xuhao.payroll.infrastructure.common.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 登录尝试管理工具类
 */
public class LoginAttemptUtil {
    private static final int MAX_ATTEMPT = 5;
    private static final int BLOCK_DURATION_MINUTES = 30;

    private static final ConcurrentHashMap<String, Integer> attemptMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> blockMap = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private LoginAttemptUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 记录登录失败
     * 
     * @param username 用户名
     */
    public static void loginFailed(String username) {
        int attempts = attemptMap.getOrDefault(username, 0) + 1;
        attemptMap.put(username, attempts);

        if (attempts >= MAX_ATTEMPT) {
            blockUser(username);
        }
    }

    /**
     * 登录成功后清除记录
     * 
     * @param username 用户名
     */
    public static void loginSucceeded(String username) {
        attemptMap.remove(username);
        blockMap.remove(username);
    }

    /**
     * 检查用户是否被锁定
     * 
     * @param username 用户名
     * @return 是否被锁定
     */
    public static boolean isBlocked(String username) {
        Long blockedUntil = blockMap.get(username);
        if (blockedUntil == null) {
            return false;
        }

        if (System.currentTimeMillis() >= blockedUntil) {
            blockMap.remove(username);
            attemptMap.remove(username);
            return false;
        }

        return true;
    }

    /**
     * 获取剩余锁定时间（分钟）
     * 
     * @param username 用户名
     * @return 剩余锁定时间，如果未被锁定返回0
     */
    public static long getRemainingBlockTime(String username) {
        Long blockedUntil = blockMap.get(username);
        if (blockedUntil == null) {
            return 0;
        }

        long remainingMs = blockedUntil - System.currentTimeMillis();
        return remainingMs > 0 ? TimeUnit.MILLISECONDS.toMinutes(remainingMs) : 0;
    }

    private static void blockUser(String username) {
        long blockUntil = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(BLOCK_DURATION_MINUTES);
        blockMap.put(username, blockUntil);

        // 安排自动解除锁定任务
        scheduler.schedule(() -> {
            blockMap.remove(username);
            attemptMap.remove(username);
        }, BLOCK_DURATION_MINUTES, TimeUnit.MINUTES);
    }

    /**
     * 获取剩余允许尝试次数
     * 
     * @param username 用户名
     * @return 剩余尝试次数
     */
    public static int getRemainingAttempts(String username) {
        return MAX_ATTEMPT - attemptMap.getOrDefault(username, 0);
    }
}