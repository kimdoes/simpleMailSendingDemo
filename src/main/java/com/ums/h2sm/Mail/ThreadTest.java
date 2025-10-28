package com.ums.h2sm.Mail;

import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThreadTest {
    public static void mian() {
        System.out.println("false test");
        try (AutoCloseResource ar = new AutoCloseResource()) {
            ar.work(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        }
}

class AutoCloseResource implements AutoCloseable {
    public void work(boolean exception) throws IOException {
        System.out.println("WORK 메서드 호출됨");

        if (exception) {
            System.out.println("work 메서드에서 오류가 발생했습니다.");
            throw new IOException("work에서 오류발생!");
        }
    }

    public void close() throws IOException {
        System.out.println("close 호출됨");
        throw new IOException("Close 과정에서 오류가 발생했습니다.");
    }
}