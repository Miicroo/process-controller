package demo.model.controller;

import exception.ProcessException;
import model.controller.BaseController;
import model.controller.ProcessController;

public class ScriptController extends BaseController {

    public ScriptController() {
        this(new ProcessController());
    }

    private ScriptController(ProcessController pc) {
        super(pc);
    }

    public String runScript(String scriptName) throws ProcessException {
        return executeCommand("./"+scriptName);
    }
}
