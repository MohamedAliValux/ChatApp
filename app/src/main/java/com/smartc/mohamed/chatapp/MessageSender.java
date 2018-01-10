package com.smartc.mohamed.chatapp;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Mido on 12/28/17.
 */

public class MessageSender extends AsyncTask<String,Void,Void> {

    Socket s ;
    PrintWriter pw;
    DataOutputStream dos;
    @Override
    protected Void doInBackground(String... voids) {

        String ip = voids[0];
        int port = Integer.valueOf(voids[1]);
        String type = voids[2];
        String message = voids[3];
        try {
            s= new Socket(ip,port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(type+message);
            pw.flush();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
