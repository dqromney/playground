package com.stgutah.playground.configuration;

/**
 * Client class.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 3:59 PM
 */
public class Client {

    private Long id;
    private String name;

    public Client(Long pId, String pName) {
        this.id = pId;
        this.name = pName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
