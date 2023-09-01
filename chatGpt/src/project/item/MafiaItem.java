package project.item;

public enum MafiaItem {
    Mafia("마피아"), Doctor("의사"), Police("경찰");

    private String Contents;

    MafiaItem(String contents) {
        Contents = contents;
    }

    public String getContents() {
        return Contents;
    }
}
