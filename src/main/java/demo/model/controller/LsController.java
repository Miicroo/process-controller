package demo.model.controller;

import exception.ProcessException;
import model.controller.BaseController;
import model.controller.ProcessController;

public class LsController extends BaseController {

    public LsController() {
        this(new ProcessController());
    }

    private LsController(ProcessController pc) {
        super(pc);
    }

    public String getDirContent(String dir) throws ProcessException {
        return executeCommand("ls "+dir+" -la");
    }
}
