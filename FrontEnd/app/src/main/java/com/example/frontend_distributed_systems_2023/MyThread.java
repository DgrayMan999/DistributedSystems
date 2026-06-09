package com.example.frontend_distributed_systems_2023;


import android.util.Log;


import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.OutputStream;
import java.io.Serializable;

import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Map;


public class MyThread extends Thread implements Serializable {
    File file;


    public MyThread(File file) throws UnknownHostException, FileNotFoundException, IOException {
        this.file = file;
    }

    Socket socket = null;
    FileInputStream fileInputStream = null;
    OutputStream outputStream = null;
    ObjectInputStream intermediateResults;
    String fileData;
    public static Map<String, Double> map;
    private static final String TAG = "MAP";


    @Override
    public void run() {
        try {


            socket = new Socket("192.168.1.25", 6999);
            // Gets the input stream of the file
            fileInputStream = new FileInputStream(file);
            outputStream = socket.getOutputStream();
            DataOutputStream dataToSend = new DataOutputStream(outputStream);
            fileData = readFileAsString(file);
            dataToSend.writeUTF(fileData);
            dataToSend.flush();
            outputStream.flush();
            socket.shutdownOutput();
            intermediateResults = new ObjectInputStream(socket.getInputStream());
            Log.e(TAG, "this is intermediate" + intermediateResults);
            map = (Map<String, Double>) intermediateResults.readObject();
            StatisticsFragment st = new StatisticsFragment();
            st.setMapInStatistics(map);
            Log.e(TAG, "this is map " + map);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (intermediateResults != null) {
                    intermediateResults.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String readFileAsString(File file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line);
            fileContent.append("\n");
        }
        reader.close();
        return fileContent.toString();
    }
}


