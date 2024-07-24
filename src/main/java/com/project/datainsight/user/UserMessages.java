package com.project.datainsight.user;

public enum UserMessages {
    USER_REGISTERED_SUCCESS("회원가입에 성공했습니다."),
    USER_ALREADY_REGISTERED("이미 등록된 회원입니다"),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다"),
    INVALID_INPUT("잘못된 입력입니다");

    private final String message;

    UserMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}