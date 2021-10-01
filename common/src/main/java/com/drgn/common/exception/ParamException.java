package com.drgn.common.exception;

/**
 *
 *
 * @author wydrgn
 */
public class ParamException extends BaseException {
    private final String code;

    public enum WrongLevel {
        ERROR("2", "错误"),
        WARNING("1", "警告"),
        WEAK_WARNING("0", "弱警告");
        public String Code;
        public String Name;

        private WrongLevel(String code, String name) {
            this.Code = code;
            this.Name = name;
        }
    }

    public ParamException(String message) {
        super(message);
        this.code = WrongLevel.WEAK_WARNING.Code;
    }

    public ParamException(String message, WrongLevel wrongLevel) {
        super(message);
        this.code = wrongLevel.Code;
    }
}
