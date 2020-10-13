package seedu.address.model.userprefs;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPatientStorageFilePath();

    Path getAppointmentStorageFilePath();

}