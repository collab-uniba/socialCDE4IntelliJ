package com.socialcdeIntellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.WindowManager;
import com.socialcdeIntellij.controller.Controller;


public class MainAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        ToolWindow tw = null;
        Tool pt = new Tool();
        pt.createToolWindowContent(project,tw);

        Controller.setFrame(WindowManager.getInstance().getFrame(project));
        Controller.setProject(project);

    }
}
