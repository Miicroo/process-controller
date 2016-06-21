package model.controller;

import model.PropertyManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProcessController {
    private static final String EXECUTABLE = PropertyManager.getStringProperty("exec.path");

    public int exitCode;

    public String executeCommand(String[] commands) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(EXECUTABLE);
        builder.redirectErrorStream(true);
        Process process;
        try {
            process = builder.start();

            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter input = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            for(String command : commands) {
                input.write(command+"\n");
                input.flush();
            }
            input.close();

            StringBuilder data = new StringBuilder();
            String line;
            while((line = output.readLine()) != null) {
                data.append(line).append("\n");
            }

            output.close();

            process.waitFor();

            exitCode = process.exitValue();
            if(exitCode != 0) {
                throw new Exception("Process returned exit code " + process.exitValue() + " and output " + data);
            }

            return data.toString();
        } catch (IOException | InterruptedException e) {
            throw new Exception("Failed to connect to environment ", e);
        }
    }

    public int getExitCode() {
        return exitCode;
    }
}
