package com.group_15.bta.objects;

import java.io.Serializable;

public class Instructor extends User implements Serializable {


    public Instructor(String newName, String newPassword) {
        super(newName, newPassword);

    }

    public Instructor(String id, String password, String name) {
        super(id, password, name);
    }
}
