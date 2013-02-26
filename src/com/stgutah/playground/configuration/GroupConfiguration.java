package com.stgutah.playground.configuration;

/**
 * Configuration Group.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 4:03 PM
 */
public class GroupConfiguration {
    private Long id;
    private String name;

    public GroupConfiguration(Long pId, String pName) {
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
