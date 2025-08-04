package com.ticxar.FeingClient.exeptions;

public class LoginException extends  RuntimeException{
    public LoginException(String msg){
        super(msg);
    }
}
