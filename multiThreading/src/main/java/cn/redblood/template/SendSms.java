package cn.redblood.template;

/**
 * 模板方法模式
 *
 * @author The_Beatles
 * @date 2022/1/2 17:11
 */

public class SendSms extends SendCustom {

    @Override
    public void to() {
        System.out.println("Mark");
    }

    @Override
    public void from() {
        System.out.println("Bill");
    }

    @Override
    public void content() {
        System.out.println("Hello World!");
    }

    @Override
    public void send() {
        System.out.println("Send sms");
    }

    public static void main(String[] args) {
        SendCustom sendC = new SendSms();
        sendC.sendMessage();
    }

}
