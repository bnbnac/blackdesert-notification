package bnbnac.discordBot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static bnbnac.discordBot.PatchNotesUtils.getNewPatchNotes;

// not very good of maintaining prevPatch
public class PatchNotesStorage {
    private Set<PatchNote> prevPatchNotes;
    private Set<PatchNote> storedPatchNotes;

    public PatchNotesStorage() {
        prevPatchNotes = new HashSet<>();
        storedPatchNotes = new HashSet<>();
    }

    // need memory control?
    public void pushPatchNotes(Set<PatchNote> curPatchNotes) {
        prevPatchNotes = this.storedPatchNotes;
        this.storedPatchNotes = curPatchNotes;
    }

    public List<String> getNews() {
        // at starting app, no announcement should be
        if (prevPatchNotes.isEmpty()) {
            return new ArrayList<>();
        }

        return getNewPatchNotes(convertToTitleSet(prevPatchNotes), convertToTitleSet(storedPatchNotes));
    }

    public Set<String> convertToTitleSet(Set<PatchNote> patchNotes) {
        Set<String> titleSet = new HashSet<>();

        for (PatchNote patchNote : patchNotes) {
            titleSet.add(patchNote.title);
        }

        return titleSet;
    }

    public Set<PatchNote> getStoredPatchNotes() {
        return storedPatchNotes;
    }
}
