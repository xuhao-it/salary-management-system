package com.xuhao.payroll.infrastructure.common.aspect;

import com.xuhao.payroll.infrastructure.common.annotation.Log;
import com.xuhao.payroll.infrastructure.common.util.IpUtil;
import com.xuhao.payroll.model.SysLog;
import com.xuhao.payroll.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 系统日志切面
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.xuhao.payroll.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        try {
            // 执行方法
            Object result = point.proceed();
            // 执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            saveLog(point, time, null);
            return result;
        } catch (Throwable e) {
            // 执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            saveLog(point, time, e);
            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Throwable e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();

        // 获取注解上的描述
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            sysLog.setOperation(log.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的参数
        if (log != null && log.recordParams()) {
            Object[] args = joinPoint.getArgs();
            try {
                String params = Arrays.toString(args);
                sysLog.setParams(params);
            } catch (Exception ex) {
                sysLog.setParams("参数解析失败");
            }
        }

        // 获取当前登录用户信息
        // TODO: 从Session或SecurityContext中获取当前用户信息
        sysLog.setUsername("当前登录用户");

        // 获取IP地址
        sysLog.setIp(IpUtil.getLocalIp());

        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setExecutionTime(time);

        // 设置状态和错误信息
        if (e != null) {
            sysLog.setStatus("失败");
            sysLog.setErrorMessage(e.getMessage());
        } else {
            sysLog.setStatus("成功");
        }

        // 保存系统日志
        sysLogService.save(sysLog);
    }
}