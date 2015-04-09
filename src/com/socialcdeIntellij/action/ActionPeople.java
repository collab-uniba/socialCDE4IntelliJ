package com.socialcdeIntellij.action;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.LabelClicked;
import com.socialcdeIntellij.shared.library.WUser;

import java.util.HashMap;

/**
 * Created by Teo on 17/03/2015.
 */
public class ActionPeople {
    private WUser[] userCategory;
    private WUser userSelected;

    public ActionPeople(HashMap<String, Object> uiData) {

        String widgetName = uiData.get("ID_action").toString();

        switch (widgetName) {
            case "lblUser":
                if(Controller.getProxy().IsWebServiceRunning())
                {
                    LabelClicked labelClicked = ((LabelClicked) uiData.get("Object"));
                    userSelected = labelClicked.getLabel().getwUser();

                    userCategory = Controller.getProxy().GetSuggestedFriends(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    for (int i = 0; i < userCategory.length; i++) {
                        if (userCategory[i].Username.equals(userSelected.Username)) {
                            /*Controller.temporaryInformation.put("User_type",
                                    "Suggested");*/
                            labelClicked.setUserType("Suggested");
                        }
                    }

                    userCategory = null;

                    userCategory = Controller.getProxy().GetFollowings(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    for (int i = 0; i < userCategory.length; i++) {
                        if (userCategory[i].Username.equals(userSelected.Username)) {
                            /*Controller.temporaryInformation.put("User_type",
                                    "Following");*/
                            labelClicked.setUserType("Following");
                        }
                    }
                    //userCategory = null;

                    WUser[] userCategory2 = Controller.getProxy().GetFollowers(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    if(userCategory.length==0 && userCategory2.length>0) {

                        for (int i = 0; i < userCategory2.length; i++) {
                            if (userCategory2[i].Username.equals(userSelected.Username)) {
                                /*Controller.temporaryInformation.put("User_type",
                                        "Followers");*/
                                labelClicked.setUserType("Followers");
                            }
                        }
                    }
                    userCategory = null;

                    userCategory = Controller.getProxy().GetHiddenUsers(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    for (int i = 0; i < userCategory.length; i++) {
                        if (userCategory[i].Username.equals(userSelected.Username)) {
                            //Controller.temporaryInformation.put("User_type", "Hidden");
                            labelClicked.setUserType("Hidden");
                        }
                    }

                    userCategory = null;

                    Controller.temporaryInformation.put("User_selected", userSelected);
                    Controller.temporaryInformation.put("UserType", labelClicked.getUserType());
                    Controller.temporaryInformation.put("PrePanel", "PeoplePanel");

                    Controller.selectDynamicWindow(6);
                    Controller.getWindow().revalidate();
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            default:
                break;
        }
    }

}
