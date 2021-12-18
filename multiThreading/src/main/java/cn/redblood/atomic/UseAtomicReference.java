package cn.redblood.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author The_Beatles
 * @date 2021/12/18 14:44
 */

public class UseAtomicReference {

    static AtomicReference<UserInfo> userRef = new AtomicReference<>();

    public static void main(String[] args) {
        UserInfo user = new UserInfo("Captain", 18);
        userRef.set(user);

        UserInfo newUser = new UserInfo("Bill", 17);
        userRef.compareAndSet(user, newUser);
        System.out.println(userRef.get().getName());
        System.out.println(userRef.get().getAge());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    static class UserInfo{
        private String name;

        private int age;

        public UserInfo(String name, int age){
            this.age = age;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}
