package com.xuhao.payroll.infrastructure.common.exception;

import com.xuhao.payroll.infrastructure.common.result.ResultCode;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    private final ResultCode resultCode;

    public BusinessException() {
        super(ResultCode.ERROR.getMessage());
        this.resultCode = ResultCode.ERROR;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public BusinessException(String message) {
        super(message);
        this.resultCode = ResultCode.ERROR;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}