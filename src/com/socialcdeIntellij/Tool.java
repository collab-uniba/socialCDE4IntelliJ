package com.socialcdeIntellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.socialcdeIntellij.controller.Controller;

import javax.swing.*;


public class Tool implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(Project project, com.intellij.openapi.wm.ToolWindow toolWindow) {

        ToolWindowManager twm= ToolWindowManager.getInstance(project);
        toolWindow = twm.registerToolWindow("SocialCDE",true, ToolWindowAnchor.RIGHT);
        Content content=ContentFactory.SERVICE.getInstance().createContent(getPanel(),"",true);
        toolWindow.getContentManager().addContent(content);

    }

    public JPanel getPanel(){

        JPanel panel = new JPanel();
        panel.add(Controller.getCurrentPanel());
        panel.setPreferredSize(Controller.getCurrentPanel().getSize());
        panel.setVisible(true);

        return panel;
    }
}
