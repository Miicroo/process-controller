package model.controller;

import exception.ProcessException;
import model.controller.ProcessController;

public class BaseController {

    private ProcessController processController;

    public BaseController(ProcessController processController) {
        this.processController = processController;
    }

    protected String executeCommand(String command) throws ProcessException {
        return executeCommands(new String[]{command});
    }

    protected String executeCommands(String[] commands) throws ProcessException {
        try {
            return processController.executeCommand(commands);
        } catch(Exception e) {
            if(processController.getExitCode() != 0) {
                throw new ProcessException("Process returned exit code " + processController.getExitCode());
            } else {
                throw new ProcessException("Failed to connect to environment ", e);
            }
        }
    }
}
