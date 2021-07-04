package red210627;

import java.util.Scanner;

/**
 * @author charlie
 */
public class Stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入回文:");
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        char[] a = new char[0];
        int length = chars.length;
        int mid = length / 2 - 1;
        int top = 0;
        for (int i = 0; i <= mid; i++) {
            a[++top] = chars[i];
        }
        int next = 0;
        if (mid % 2 == 0) {
            next = mid + 1;
        } else {
            next = mid + 2;
        }

        for (int i = next; i <= length-1; i++) {
            if (chars[next] != a[top]) {
                break;
            }
            top--;
        }

        if (top == 0) {
            System.out.println("这是回文！");
        }else {
            System.out.println("这不是回文！！！");
        }

    }
}
