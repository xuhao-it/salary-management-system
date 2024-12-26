package com.xuhao.payroll.infrastructure.common.util;

import com.xuhao.payroll.model.SysUser;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 会话管理工具类
 */
public class SessionUtil {
    private static final Map<String, SessionInfo> sessions = new HashMap<>();
    private static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30分钟超时

    private SessionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 创建新会话
     * 
     * @param user 用户信息
     * @return 会话token
     */
    public static String createSession(SysUser user) {
        String token = UUID.randomUUID().toString();
        sessions.put(token, new SessionInfo(user));
        return token;
    }

    /**
     * 验证会话是否有效
     * 
     * @param token 会话token
     * @return 是否有效
     */
    public static boolean isValidSession(String token) {
        SessionInfo session = sessions.get(token);
        if (session == null) {
            return false;
        }

        if (System.currentTimeMillis() - session.getLastAccessTime() > SESSION_TIMEOUT) {
            sessions.remove(token);
            return false;
        }

        session.updateAccessTime();
        return true;
    }

    /**
     * 获取当前会话用户
     * 
     * @param token 会话token
     * @return 用户信息，如果会话无效则返回null
     */
    public static SysUser getCurrentUser(String token) {
        if (!isValidSession(token)) {
            return null;
        }
        return sessions.get(token).getUser();
    }

    /**
     * 移除会话
     * 
     * @param token 会话token
     */
    public static void removeSession(String token) {
        sessions.remove(token);
    }

    /**
     * 清理过期会话
     */
    public static void cleanExpiredSessions() {
        long currentTime = System.currentTimeMillis();
        sessions.entrySet().removeIf(entry -> currentTime - entry.getValue().getLastAccessTime() > SESSION_TIMEOUT);
    }

    /**
     * 会话信息类
     */
    private static class SessionInfo {
        private final SysUser user;
        private long lastAccessTime;

        public SessionInfo(SysUser user) {
            this.user = user;
            this.lastAccessTime = System.currentTimeMillis();
        }

        public SysUser getUser() {
            return user;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public void updateAccessTime() {
            this.lastAccessTime = System.currentTimeMillis();
        }
    }
}