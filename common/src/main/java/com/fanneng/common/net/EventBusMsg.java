package com.fanneng.common.net;

/**
 * @author ：王文彬 on 2018/5/23 12：33
 * @describe：EventBus传递内容的封装 <p>eg:EventBus.getDefault().post(new EventBusMsg<T>(tat,T));<p/>
 * @email：wwb199055@126.com
 */
public class EventBusMsg<T> {

  private T event;

  private String tag;

  /**
   * @param tag 传入的Tag值，可以根据tag区分不同EventBus传过来的值
   */
  public EventBusMsg(String tag) {
    this.tag = tag;
  }

  /**
   * @param event 传入的具体内容
   */
  public EventBusMsg(T event) {
    this.event = event;
  }

  public EventBusMsg(String tag, T event) {
    this.tag = tag;
    this.event = event;
  }


  public String getTag() {
    return tag;
  }

  public T getData() {
    return event;
  }

}
