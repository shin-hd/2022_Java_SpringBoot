package ch2.Item9;

import java.io.*;

/*
 * try-finally보다는 try-with-resources를 사용하라
 */
public class Item9 {

    /*
     * try-finally 문제점
     *
     * 1. 자원 수가 증가할수록 try-finally 중첩
     * 2. 예외가 try, finally 각각에서 모두 발생
     * 3. 한 예외가 다른 예외를 감춰버림
     */

    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    // 둘 이상의 자원에서 try-finally
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // ...
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /*
     * try-with-resources
     * 사용하려면 AutoCloseable을 반드시 구현해야함
     *
     * 장점
     * 1. 짧고 읽기 수월하며, 문제 진단에도 좋음
     * 2. readLine, close 양쪽에서 예외 발생해도 (suppressed 달고) 출력됨
     * 3. catch 절로 try 중첩 없이 다수의 예외 처리 가능
     *
     */

    static String firstLineOfFile2(String path) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        }
    }

    static void copy2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            // ...
        }
    }

    static String firstLineOfFile3(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
}
