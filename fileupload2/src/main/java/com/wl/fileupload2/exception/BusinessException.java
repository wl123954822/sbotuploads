package com.wl.fileupload2.exception;

import com.wl.fileupload2.Enums.ResultEnum;

public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BusinessException(String code,String message){
        super(message);
        this.code=code;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
