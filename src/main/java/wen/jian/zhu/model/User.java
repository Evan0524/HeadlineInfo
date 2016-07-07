package wen.jian.zhu.model;

/**
 * Created by Administrator on 2016/7/7.
 */
public class User {
    public String name;
    public int age;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
