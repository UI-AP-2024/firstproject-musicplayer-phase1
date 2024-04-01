package controller;

import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;

import java.util.regex.Pattern;

public class ListenerController {
        private ListenerModel listener;
        public ListenerModel getListener() {
                return listener;
        }
        public void setListener(ListenerModel listener) {
                this.listener = listener;
        }

        private static ListenerController listenerController;

        public static ListenerController getListenerController() {
                if (listenerController == null)
                        listenerController = new ListenerController();
                return listenerController;
        }
}
