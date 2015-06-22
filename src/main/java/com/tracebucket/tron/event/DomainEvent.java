package com.tracebucket.tron.event;

import com.tracebucket.tron.cqrs.support.Command;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.Date;

/**
 * @author ffl
 * @since 06-01-2015
 * @version 0.1
 */

public class DomainEvent<T> extends Event<T> {

    private static final String prefix = "DomainEvent|";





    private String cid;

    private Date timestamp;

    public DomainEvent(Class aClass){
        super(aClass);
    }

    public DomainEvent(Headers headers, T data) {
        super(headers, data);
        init();
    }

    public DomainEvent(Headers headers, T data, Consumer<Throwable> errorConsumer) {
        super(headers, data, errorConsumer);
        init();
    }

    public DomainEvent(T data) {
        super(data);
        init();
    }

    public static <T> DomainEvent<T> wrap(T obj) {
        return new DomainEvent<T>(obj);
    }


    private void init(){
        //this.cid = UUID.randomUUID().toString();
        timestamp = new Date();

    }

    public DomainEvent from(Command command){
        this.getHeaders().setAll(command.getHeaders().asMap());
        this.setCid(command.getCid());
        return this;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static String name(String event){
        return new StringBuilder()
                .append(prefix)
                .append(event)
                .toString();
    }
}
