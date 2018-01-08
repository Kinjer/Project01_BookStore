package com.lanou.bookstore.user.domain;

/**
 * Created by dllo on 17/12/22.
 */
public class VerifyBean {
    private String verifyMsg;
    private boolean passVerify;

    public String getVerifyMsg() {
        return verifyMsg;
    }

    public void setVerifyMsg(String verifyMsg) {
        this.verifyMsg = verifyMsg;
    }

    public boolean isPassVerify() {
        return passVerify;
    }

    public void setPassVerify(boolean passVerify) {
        this.passVerify = passVerify;
    }
}
