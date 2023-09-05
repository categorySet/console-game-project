package item;

public enum BasicItem {
    title("칭호"), color("스킨"), edition("둘다");
    private String contents;

    BasicItem(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
