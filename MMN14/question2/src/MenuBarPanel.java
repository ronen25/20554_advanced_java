/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 2
 */

import javax.swing.*;
import java.util.*;
import java.awt.*;

/** The phone book application's main menu bar. */
public class MenuBarPanel extends JPanel {
    // Constant strings for menus
    private String FILE_MENU = "File";
    private String ACTIONS_MENU = "Actions";
    private String SEARCH_MENU = "Search";

    private String FILE_LOAD_ITEM = "Load...";
    private String FILE_SAVE_ITEM = "Save...";
    private String ACTIONS_ADD_ITEM = "Add Item";
    private String ACTIONS_REMOVE_ITEM = "Remove Item";
    private String ACTIONS_EDIT_ITEM = "Edit Item...";
    private String SEARCH_SEARCH_ITEM = "Search by Name...";

    // Properties
    private JMenuBar menubar;
    private JMenu mnuFile, mnuActions, mnuSearch;
    private JMenuItem itmSave, itmLoad, itmAdd, itmRemove, itmEdit, itmSearch;

    //  Cnstr.
    public MenuBarPanel() {
        initLayout();
        initUI();
    }

    /** Initializes panel layout. */
    private void initLayout() {
        // Initialize and set layout
        this.setLayout(new BorderLayout());
    }

    /** Initializes panel UI, including the menubar and the submenus. */
    private void initUI() {
        // Initialize menubar
        menubar = new JMenuBar();

        // NOTE: When initializing the menus, we do not put any actions.
        // This will be done by the user of the menu panel, since we do not want
        // to mix application business logic with it's UI.

        // Initialize the File menu
        mnuFile = new JMenu(FILE_MENU);
        itmSave = new JMenuItem(FILE_SAVE_ITEM);
        itmLoad = new JMenuItem(FILE_LOAD_ITEM);

        mnuFile.add(itmSave);
        mnuFile.add(itmLoad);

        // Initialize the Actions menu
        mnuActions = new JMenu(ACTIONS_MENU);
        itmAdd = new JMenuItem(ACTIONS_ADD_ITEM);
        itmRemove = new JMenuItem(ACTIONS_REMOVE_ITEM);
        itmEdit = new JMenuItem(ACTIONS_EDIT_ITEM);

        mnuActions.add(itmAdd);
        mnuActions.add(itmRemove);
        mnuActions.add(itmEdit);

        // Initialize the Search menu
        mnuSearch = new JMenu(SEARCH_MENU);
        itmSearch = new JMenuItem(SEARCH_SEARCH_ITEM);

        mnuSearch.add(itmSearch);

        // Add menus to menu bar
        menubar.add(mnuFile);
        menubar.add(mnuActions);
        menubar.add(mnuSearch);

        // Add menubar to panel
        this.add(menubar, BorderLayout.CENTER);
    }

    /** Gets the "Save" menu. Used for adding ActionListeners. */
    public JMenuItem getSaveMenu() {
        return itmSave;
    }

    /** Gets the "Load" menu. Used for adding ActionListeners. */
    public JMenuItem getLoadMenu() {
        return itmLoad;
    }

    /** Gets the "Add" menu. Used for adding ActionListeners. */
    public JMenuItem getAddMenu() {
        return itmAdd;
    }

    /** Gets the "Remove" menu. Used for adding ActionListeners. */
    public JMenuItem getRemoveMenu() {
        return itmRemove;
    }

    /** Gets the "Edit" menu. Used for adding ActionListeners. */
    public JMenuItem getEditMenu() {
        return itmEdit;
    }

    /** Gets the "Search" menu. Used for adding ActionListeners. */
    public JMenuItem getSearchMenu() {
        return itmSearch;
    }
}
