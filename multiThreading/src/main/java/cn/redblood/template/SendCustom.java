package cn.redblood.template;

import java.time.LocalDateTime;

/**
 * @author The_Beatles
 * @date 2022/1/2 17:09
 */

public abstract class SendCustom {

    public abstract void to();

    public abstract void from();

    public abstract void content();

    public void date() {
        System.out.println(LocalDateTime.now());
    }

    public abstract void send();

    /**
     * 框架方法
     */
    public void sendMessage() {
        to();
        from();
        content();
        date();
        send();
    }

}
