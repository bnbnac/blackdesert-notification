package bnbnac.discordBot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static bnbnac.discordBot.PatchNotesUtils.getNewPatchNotes;

public class PatchNotesStorage {
    private Set<PatchNote> prevPatchNotes;
    private Set<PatchNote> curPatchNotes;

    public PatchNotesStorage() {
        prevPatchNotes = new HashSet<>();
        curPatchNotes = new HashSet<>();
    }

    // need memory control?
    public void pushPatchNotes(Set<PatchNote> curPatchNotes) {
        if (curPatchNotes.isEmpty()) {
            return;
        }
        prevPatchNotes = this.curPatchNotes;
        this.curPatchNotes = curPatchNotes;
    }

    public List<String> getNews() {
        // at starting app, no announcement should be
        if (prevPatchNotes.isEmpty()) {
            return new ArrayList<>();
        }

        return getNewPatchNotes(convertToTitleSet(prevPatchNotes), convertToTitleSet(curPatchNotes));
    }

    public Set<String> convertToTitleSet(Set<PatchNote> patchNotes) {
        Set<String> titleSet = new HashSet<>();

        for (PatchNote patchNote : patchNotes) {
            titleSet.add(patchNote.title);
        }

        return titleSet;
    }

    public Set<PatchNote> getCurPatchNotes() {
        return curPatchNotes;
    }
}
