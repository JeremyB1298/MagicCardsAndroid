package controllers;

public interface InterfaceCallBackController<T> {
    public void onWorkDone(T result);
}