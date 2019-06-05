package net.wanho.pojo;

import lombok.Data;

/**
 * Created by 十四 on 2019/6/5.
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String password;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.name = username;
        this.password = password;
    }

    public User() {
    }
}
