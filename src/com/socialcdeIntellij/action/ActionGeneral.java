package com.socialcdeIntellij.action;

import com.socialcdeIntellij.controller.Controller;
import java.awt.event.*;
import java.util.HashMap;


public class ActionGeneral implements ActionListener, FocusListener , MouseListener{

    private HashMap<String, Object> uiData;


    public void actionPerformed(ActionEvent event) {
        switch (Controller.getWindowName()) {

            case "Registration":
                uiData = Controller.getRegistrationPanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action",event.getActionCommand());

                new ActionRegistration(uiData);

                break;

            case "Login":
                uiData = Controller.getLoginPanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action", event.getActionCommand());

                new ActionLogin(uiData);

                break;

            case "Profile":
                uiData = Controller.getProfilePanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action", event.getActionCommand());

                new ActionProfile(uiData);

                switch (Controller.getDynamicPanelName()){

                    case "Home":

                        uiData = Controller.getHomePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());
                        uiData.put("Object", event.getSource());

                        new ActionHome(uiData);

                        break;

                    case "Settings":

                        uiData = Controller.getSettingPanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());
                        uiData.put("Object", event.getSource());

                        new ActionSetting(uiData);

                        break;

                    case "People":
                        uiData = Controller.getHomePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());
                        uiData.put("Object", event.getSource());

                        new ActionPeople(uiData);

                        break;

                    case "HomeTimeline":
                        uiData = Controller.getHomePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());

                        new ActionHomeTimeline(uiData);

                        break;

                    case "UserTimeline":
                        uiData = Controller.getUserTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());

                        new ActionUserTimeline(uiData);

                        break;

                    case "InteractiveTimeline":
                        uiData = Controller.getInteractiveTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());

                        new ActionInteractiveTimeline(uiData);

                        break;

                    case "IterationTimeline":
                        uiData = Controller.getIterationTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getActionCommand());

                        new ActionIterationTimeline(uiData);

                        break;

                }

                break;

        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent event) {
            switch (Controller.getWindowName()) {

                case "Registration":
                    uiData = Controller.getRegistrationPanel().getData();
                    uiData.put("Event", event);
                    uiData.put("Event_type", event.getID());
                    uiData.put("ID_action",event.getComponent().getName());//cambiare

                    new ActionRegistration(uiData);

                    break;

                case "Login":
                    uiData = Controller.getLoginPanel().getData();
                    uiData.put("Event", event);
                    uiData.put("Event_type", event.getID());
                    uiData.put("ID_action", event.getComponent().getName());

                    new ActionLogin(uiData);

                    break;

                case "Profile":
                    uiData = Controller.getProfilePanel().getData();
                    uiData.put("Event", event);
                    uiData.put("Event_type", event.getID());
                    uiData.put("ID_action", event.getComponent().getName());

                    new ActionProfile(uiData);

                    switch (Controller.getDynamicPanelName()){

                        case "Home":
                            uiData = Controller.getHomePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());

                            new ActionHome(uiData);
                            break;

                        case "Settings":

                            uiData = Controller.getSettingPanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionSetting(uiData);

                            break;

                        case "People":
                            uiData = Controller.getPeoplePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionPeople(uiData);

                            break;

                        case "HomeTimeline":
                            uiData = Controller.getHomeTimelinePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionHomeTimeline(uiData);

                            break;

                        case "UserTimeline":
                            uiData = Controller.getUserTimelinePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionUserTimeline(uiData);

                            break;

                        case "InteractiveTimeline":
                            uiData = Controller.getInteractiveTimelinePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionInteractiveTimeline(uiData);

                            break;

                        case "IterationTimeline":
                            uiData = Controller.getIterationTimelinePanel().getData();
                            uiData.put("Event", event);
                            uiData.put("Event_type", event.getID());
                            uiData.put("ID_action", event.getComponent().getName());
                            uiData.put("Object", event.getComponent());

                            new ActionIterationTimeline(uiData);

                            break;

                    }
                    break;


        }

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        switch (Controller.getWindowName()) {

            case "Registration":
                uiData = Controller.getRegistrationPanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action", event.getComponent().getName());

                new ActionRegistration(uiData);

                break;

            case "Login":
                uiData = Controller.getLoginPanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action", event.getComponent().getName());

                new ActionLogin(uiData);

                break;

            case "Profile":
                uiData = Controller.getProfilePanel().getData();
                uiData.put("Event", event);
                uiData.put("Event_type", event.getID());
                uiData.put("ID_action", event.getComponent().getName());

                new ActionProfile(uiData);

                switch (Controller.getDynamicPanelName()){

                    case "Home":
                        uiData = Controller.getHomePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionHome(uiData);
                        break;

                    case "Settings":

                        uiData = Controller.getSettingPanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionSetting(uiData);

                        break;

                    case "People":
                        uiData = Controller.getPeoplePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());


                        new ActionPeople(uiData);

                        break;

                    case "HomeTimeline":
                        uiData = Controller.getHomeTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionHomeTimeline(uiData);

                        break;

                    case "UserTimeline":
                        uiData = Controller.getUserTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionUserTimeline(uiData);

                        break;

                    case "InteractiveTimeline":
                        uiData = Controller.getInteractiveTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionInteractiveTimeline(uiData);

                        break;

                    case "IterationTimeline":
                        uiData = Controller.getIterationTimelinePanel().getData();
                        uiData.put("Event", event);
                        uiData.put("Event_type", event.getID());
                        uiData.put("ID_action", event.getComponent().getName());
                        uiData.put("Object", event.getComponent());

                        new ActionIterationTimeline(uiData);

                        break;

                }
                break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
