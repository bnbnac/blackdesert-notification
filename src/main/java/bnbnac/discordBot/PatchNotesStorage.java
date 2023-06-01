package bnbnac.discordBot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static bnbnac.discordBot.PatchNotesComparator.getNewPatchNotes;

// not very good of maintaining prevPatch
public class PatchNotesStorage {
    public Set<PatchNote> prevPatchNotes;
    public Set<PatchNote> curPatchNotes;

    public PatchNotesStorage() {
        prevPatchNotes = new HashSet<>();
        curPatchNotes = new HashSet<>();
    }

    // need memory control?
    public List<String> getNews(Set<PatchNote> curPatchNotes) {
        this.prevPatchNotes = this.curPatchNotes;
        this.curPatchNotes = curPatchNotes;

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
}
