package com.smartc.mohamed.chatapp;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView mTxtIp,mTextPort;
    EditText mEdIp,mEdPort;
    Button mBtnConnect;
    String Ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", "Ninja");
        wifiConfig.preSharedKey = String.format("\"%s\"", "Ninja Password");

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
        Ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        init();

    }



    private void init() {
        mTextPort = (TextView) findViewById(R.id.txt_port);
        mTxtIp = (TextView) findViewById(R.id.txt_ip);
        mEdIp = (EditText) findViewById(R.id.ed_ip);
        mEdPort = (EditText) findViewById(R.id.ed_port);
        mBtnConnect = (Button) findViewById(R.id.btn_connect);
        mBtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdPort.getText().length()<5||validate(mEdIp.getText().toString())) {
                    StaticData.Ip = mEdIp.getText().toString();
                    StaticData.SendPort = mEdPort.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Error Port or Ip",Toast.LENGTH_LONG).show();
                }
            }
        });
        int randomPort = (int)(Math.random()*9000)+1000;
        StaticData.ReceivePort = randomPort;

        mTextPort.append(": "+randomPort);
        mTxtIp.append(": "+Ip);
    }

    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }
}
